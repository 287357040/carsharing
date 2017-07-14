package com.carsharing.server.controller.front;

import com.carsharing.server.service.DriverService;
import com.carsharing.server.service.UserService;
import com.carsharing.server.util.JsonResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by hucl on 2017/7/12.
 */
@RestController
@RequestMapping("/front/driver")
public class DriverController {

//    @Resource
//    private DriverService driverService;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST},value = "/regToDriver")
    public JsonResponse<String> regToDriver(){

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST},value = "/publishNewRoute")
    public JsonResponse<String> publishNewRoute(){

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }





}
