package com.carsharing.server.controller.front;

import com.carsharing.server.service.DriverService;
import com.carsharing.server.service.UserService;
import com.carsharing.server.util.JsonResponse;
import org.omg.CORBA.Request;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by hucl on 2017/7/12.
 * 司机信息设置
 */
@RestController
@RequestMapping("/front/driver")
public class DriverController {

    @Resource
    private DriverService driverService;

    /**
     * 员工首次注册司机时增加司机的信息
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST},value = "/regToDriver")
    public JsonResponse<String> regToDriver(){

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }

    /**
     * 更新司机的信息：司机的车辆信息
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST},value = "/updateDriverInfo")
    public JsonResponse<String> updateDriverInfo(){

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }
}
