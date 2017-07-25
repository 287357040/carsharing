package com.carsharing.server.controller.front;

import com.carsharing.server.constant.SystemCode;
import com.carsharing.server.entity.RideDemand;
import com.carsharing.server.entity.RideRoute;
import com.carsharing.server.entity.User;
import com.carsharing.server.service.RideDemandService;
import com.carsharing.server.util.JsonResponse;
import com.carsharing.server.util.SessionUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by hucl on 2017/7/19.
 */
@RestController
@RequestMapping("/front/demand")
public class RideDemandController {

    @Resource
    private RideDemandService rideDemandService;

    /**
     * 发布乘车需求
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/publishRideDemand")
    public JsonResponse<String> publishRideRequirement() {

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }

    /**
     * 删除乘车需求
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/deleteRideDemand")
    public JsonResponse<String> deleteRideDemand() {

        JsonResponse<String> result = new JsonResponse<String>();

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
     * @param userNo 员工号
     * @param state  需求状态
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/getRideDemandsByUserNo")
    public JsonResponse<List<RideDemand>> getRideDemandsByUserNo(int userNo, int state,HttpServletRequest request) {
        JsonResponse<List<RideDemand>> result = new JsonResponse<List<RideDemand>>(SystemCode.FAILURE);
        User user = SessionUtil.getUser(request);
        if (null == user) {
            result.setRes(SystemCode.NO_LOGIN);
            return result;
        }
        List<RideDemand> demands = rideDemandService.getRideDemandsByUserNo(user.getUserNo(), 1);
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
    public JsonResponse<List<RideDemand>> getRideDemandsByRouteId(int routeId, HttpServletRequest request) {
        JsonResponse<List<RideDemand>> result = new JsonResponse<List<RideDemand>>(SystemCode.FAILURE);
        User user = SessionUtil.getUser(request);
        if (null == user) {
            result.setRes(SystemCode.NO_LOGIN);
            return result;
        }
        List<RideDemand> demands = rideDemandService.getRideDemandsByRouteId(routeId);
        if (null != demands) {
            result.setRes(SystemCode.SUCCESS);
            result.setObj(demands);
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
    public JsonResponse<List<RideDemand>> matchRideDemandsByRoute(RideRoute route) {
        JsonResponse<List<RideDemand>> result = new JsonResponse<List<RideDemand>>(SystemCode.FAILURE);

        return result;
    }


}
