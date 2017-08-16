package com.carsharing.server.controller.front;

import com.carsharing.server.constant.SystemCode;
import com.carsharing.server.controller.AbstractController;
import com.carsharing.server.entity.RideDemand;
import com.carsharing.server.entity.RideRoute;
import com.carsharing.server.entity.User;
import com.carsharing.server.service.RideDemandService;
import com.carsharing.server.service.UserService;
import com.carsharing.server.util.JsonResponse;
import com.carsharing.server.util.PositionUtil;
import com.carsharing.server.util.SessionUtil;
import com.carsharing.server.vo.RideDemandVo;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.Position;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hucl on 2017/7/19.
 */
@RestController
@RequestMapping("/front/demand")
public class RideDemandController extends AbstractController {

    private Logger lo = Logger.getLogger(RideDemandController.class);

    @Resource
    private RideDemandService rideDemandService;

    @Resource
    private UserService userService;

    /**
     * 发布乘车需求
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/publishRideDemand")
    public JsonResponse<RideDemand> publishRideRequirement(RideDemand demand, HttpServletRequest request) {

        JsonResponse<RideDemand> result = new JsonResponse<RideDemand>(SystemCode.FAILURE);

        User user = SessionUtil.getUser(request);

        try {
            User serUser = userService.selectByPrimaryKey(user.getUserNo());
            demand.setUserNo(user.getUserNo());
            demand.setUserName(user.getUserName());
            demand.setSex(user.getSex());
            Integer demandId = rideDemandService.insertSelective(demand);
            result.setObj(demand);
            result.setRes(SystemCode.SUCCESS);
        } catch (Exception e) {
            lo.error("publish ride demand failed!", e);
            logError(request, "publish ride demand failed!", e);
        }
        return result;
    }

    /**
     * 删除乘车需求
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/deleteRideDemand")
    public JsonResponse<Integer> deleteRideDemand(Integer demandId, HttpServletRequest request) {

        JsonResponse<Integer> result = new JsonResponse<Integer>(SystemCode.FAILURE);

        User user = SessionUtil.getUser(request);
        RideDemand demand = rideDemandService.selectByPrimaryKey(demandId);
        // 是否存在id
        if (null == demand) {
            result.setRes(SystemCode.OBJ_NOT_EXISTS);
            return result;
        }
        // 如果删除的不是用户自己的需求 则报错
        if (!user.getUserNo().equals(demand.getUserNo())) {
            result.setRes(SystemCode.ILLEGAL_ARGUMENT);
            return result;
        }
        // state 需求单状态 0:发布中 1：已接受 2:取消发布  3：执行中 4：结束
        // 需求状态>1时 不能执行
        if (demand.getState() > 1) {
            result.setRes(SystemCode.PARAM_ERROR);
            return result;
        }

        try {
            demand.setState(2);
            demand.setRouteId(0);

            rideDemandService.updateByPrimaryKeySelective(demand);
            result.setRes(SystemCode.SUCCESS);
        } catch (Exception e) {
            lo.error("delete ride demand failed!", e);
            logError(request, "delete ride demand failed!", e);
        }
        return result;
    }

    /**
     * 修改乘车需求
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/updateRideDemand")
    public JsonResponse<String> updateRideDemand() {

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }

    /**
     * 需要用户登录之后才有权限查看自己发布的需求
     *
     * @param state 需求状态
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/getRideDemands")
    public JsonResponse<List<RideDemand>> getRideDemands(Integer state, HttpServletRequest request) {
        JsonResponse<List<RideDemand>> result = new JsonResponse<List<RideDemand>>(SystemCode.FAILURE);
        User user = SessionUtil.getUser(request);
        if (null == user) {
            result.setRes(SystemCode.NO_LOGIN);
            return result;
        }
        List<RideDemand> demands = rideDemandService.getRideDemandsByUserNo(user.getUserNo(), state);
        if (null != demands) {
            result.setRes(SystemCode.SUCCESS);
            result.setObj(demands);
        }
        return result;
    }

    /**
     * 根据路线Id来获取当前的乘客需求
     *
     * @param routeId 路线Id
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/getRideDemandsByRouteId")
    public JsonResponse<List<RideDemandVo>> getRideDemandsByRouteId(Integer routeId, HttpServletRequest request) {
        JsonResponse<List<RideDemandVo>> result = new JsonResponse<>(SystemCode.FAILURE);
        List<RideDemandVo> demadVos = new ArrayList<>();

        List<RideDemand> demands = rideDemandService.getRideDemandsByRouteId(routeId);
        for (RideDemand demand : demands) {
            RideDemandVo demandVo = new RideDemandVo(demand);
            User user = userService.selectByPrimaryKey(demand.getUserNo());
            demandVo.setMobile(user.getMobile());
            demandVo.setUserName(user.getUserName());
            demadVos.add(demandVo);
        }
        if (null != demands) {
            result.setRes(SystemCode.SUCCESS);
            result.setObj(demadVos);
        }
        return result;
    }

    /**
     * 根据路线信息来匹配合适的乘客需求
     *
     * @param route 路线信息
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/matchRideDemandsByRoute")
    public JsonResponse<List<RideDemandVo>> matchRideDemandsByRoute(RideRoute route, HttpServletRequest request) {
        JsonResponse<List<RideDemandVo>> result = new JsonResponse<List<RideDemandVo>>(SystemCode.FAILURE);

        User user = userService.selectByPrimaryKey(SessionUtil.getUser(request).getUserNo());
        // 先不从服务器去判断用户是否是司机
        if (!user.getIsDriver()) {
            result.setRes(SystemCode.NO_PRI);
            return result;
        }
        List<RideDemandVo> resDemandList = new ArrayList<RideDemandVo>();

        List<RideDemand> demandList = rideDemandService.getRideDemandsByState(0);
        if (null != demandList) {
            for (RideDemand demand : demandList) {
                RideDemandVo demandVo = new RideDemandVo(demand);
                if (demandVo.getEndArea().equals(route.getEndArea())) {
                    demandVo.setMatchDegree(60);
                    resDemandList.add(demandVo);

                    double distant = PositionUtil.getDistanceFromTwoPoints(route.getEndLatitude(), route.getEndLongitude(), demand.getEndLatitude(), demand.getEndLongitude());
                    if (distant < 1) {
                        demandVo.setMatchDegree(95);
                    } else if (distant < 3) {
                        demandVo.setMatchDegree(90);
                    } else if (distant < 5) {
                        demandVo.setMatchDegree(80);
                    } else if (distant < 10) {
                        demandVo.setMatchDegree(70);
                    }
                }
            }
        }
        // 排序
        if (resDemandList.size() > 0) {
            Collections.sort(resDemandList, new Comparator<RideDemandVo>() {
                public int compare(RideDemandVo arg0, RideDemandVo arg1) {
                    return arg1.getMatchDegree().compareTo(arg0.getMatchDegree());
                }
            });
        }
        result.setObj(resDemandList);
        result.setRes(SystemCode.SUCCESS);
        return result;
    }


}
