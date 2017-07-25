package com.carsharing.server.controller.front;

import com.carsharing.server.constant.SystemCode;
import com.carsharing.server.entity.RideDemand;
import com.carsharing.server.entity.RideRoute;
import com.carsharing.server.service.RideRouteService;
import com.carsharing.server.util.JsonResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hucl on 2017/7/19.
 */
@RestController
@RequestMapping("/front/route/")
public class RideRouteController {

    @Resource
    private RideRouteService rideRouteService;

    /**
     * 司机发布新的路线
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/publishNewRoute")
    public JsonResponse<String> publishNewRoute() {

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }

    /**
     * 司机触发删除一个拼车订单路线
     * 1、删除一个订单路线的前提是没有乘客加入当前路线
     * 2、不删除记录，只是把当前路线Id的state设置为删除状态
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/deleteRoute")
    public JsonResponse<String> deleteRoute() {

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }

    /**
     * 司机触发开始一个拼车订单路线
     * 1、修改当前拼车路线的状态为开始状态
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/startRoute")
    public JsonResponse<String> startRoute() {

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }

    /**
     * 司机完成了一次拼车路线
     * 1、要修改路线订单的状态--已完成状态
     * 2、更新司机完成拼车订单的次数，更新乘客的乘车次数
     *
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/finishRoute")
    public JsonResponse<String> finishRoute() {

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }

    /**
     * 根据乘客的去修信息来匹配合适的司机路线
     *
     * @param demand 路线信息
     * @return
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/matchRideRouteByDemand")
    public JsonResponse<List<RideRoute>> matchRideRouteByDemand(RideRoute demand) {
        JsonResponse<List<RideRoute>> result = new JsonResponse<List<RideRoute>>(SystemCode.FAILURE);

        return result;
    }
}
