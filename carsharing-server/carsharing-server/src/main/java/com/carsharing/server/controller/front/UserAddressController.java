package com.carsharing.server.controller.front;

import com.carsharing.server.constant.SystemCode;
import com.carsharing.server.controller.AbstractController;
import com.carsharing.server.entity.User;
import com.carsharing.server.entity.UserAddress;
import com.carsharing.server.service.RideRouteService;
import com.carsharing.server.service.UserAddressService;
import com.carsharing.server.util.JsonResponse;
import com.carsharing.server.util.SessionUtil;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by hucl on 2017/7/19.
 * 用户常用地址设置
 */
@RestController
@RequestMapping("/front/useraddress")
public class UserAddressController extends AbstractController {
    private Logger lo = Logger.getLogger(UserAddressController.class);

    @Resource
    private UserAddressService userAddressService;

    /**
     * 新增员工常用地址
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/addNewAddress")
    public JsonResponse<Integer> addNewAddress(@RequestBody UserAddress userAddress, HttpServletRequest request) {
        JsonResponse<Integer> result = new JsonResponse<Integer>(SystemCode.FAILURE);
        User user = SessionUtil.getUser(request);
        if (userAddress == null) {
            result.setRes(SystemCode.FAILURE);
            return result;
        }
        userAddress.setUserNo(user.getUserNo());

        try{
            userAddressService.insertSelective(userAddress);
            result.setRes(SystemCode.SUCCESS);
        }catch(Exception e){
            lo.error("新增地址失败", e);
            logError(request, "[新增地址失败]", e);
        }
        return result;
    }


    /**
     * 更新员工常用地址
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/updateAddress")
    public JsonResponse<Integer> updateAddress(@RequestBody UserAddress userAddress, HttpServletRequest request) {

        JsonResponse<Integer> result = new JsonResponse<Integer>(SystemCode.FAILURE);
        User user = SessionUtil.getUser(request);
        if (userAddress == null) {
            result.setRes(SystemCode.FAILURE);
            return result;
        }
        userAddress.setUserNo(user.getUserNo());

        try{
            userAddressService.updateByPrimaryKeySelective(userAddress);
            result.setRes(SystemCode.SUCCESS);
        }catch(Exception e){
            lo.error("修改地址失败", e);
            logError(request, "[修改地址失败]", e);
        }
        return result;
    }
}
