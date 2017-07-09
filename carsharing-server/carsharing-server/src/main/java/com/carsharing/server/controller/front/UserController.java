package com.carsharing.server.controller.front;

import com.carsharing.server.constant.SystemCode;
import com.carsharing.server.controller.AbstractController;
import com.carsharing.server.entity.User;
import com.carsharing.server.service.UserService;
import com.carsharing.server.util.JsonResponse;
import com.carsharing.server.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/front/user/user")
public class UserController extends AbstractController {
    private Logger lo = Logger.getLogger(UserController.class);

    @Resource
    private UserService userService;

    //@GetMapping("phoneIsExit")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},value = "phoneIsExit")
    public JsonResponse<String> phoneIsExit(String phone) {
        JsonResponse<String> result = new JsonResponse<String>();

        result.setRes(SystemCode.PHONE_NOT_EXISTS);
        return result;
    }
    /**
     * 会员详情
     */
    //@GetMapping("/checkUserPhone")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},value = "/checkUserPhone")
    public JsonResponse<User> checkUserPhone(Integer userId,
                                             HttpServletRequest request) {
        JsonResponse<User> result = new JsonResponse<User>(SystemCode.FAILURE);
        lo.info("getUserById...");
        User user = SessionUtil.getUser(request);
        if (null == user) {
            result.setRes(SystemCode.NO_LOGIN);
            return result;
        } else {
            if (StringUtils.isBlank(user.getPhone())) {
                lo.info("user.getPhone()为空或者null");
                result.setRes(SystemCode.NO_LOGIN);
                return result;
            } else {
                user = userService.selectByPrimaryKey(user.getUserId());

                if (user != null) {
                    result.setRes(SystemCode.SUCCESS);
                    result.setObj(user);
                }
            }
        }
        return result;
    }

    /**
     * 会员详情
     */
    //@GetMapping("/getUserById")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},value = "/getUserById")
    public JsonResponse<User> getUserById(Integer userId,
                                          HttpServletRequest request) {
        JsonResponse<User> result = new JsonResponse<User>(SystemCode.FAILURE);
        lo.info("getUserById...");

        User user = userService.selectByPrimaryKey(userId);
            if (user != null) {
                result.setRes(SystemCode.SUCCESS);
                result.setObj(user);
            }
//        User user = SessionUtil.getUser(request);
//        if (null == user) {
//            result.setRes(SystemCode.NO_LOGIN);
//            return result;
//        } else {
//            user = userService.selectByPrimaryKey(user.getUserId());
//            if (user != null) {
//                result.setRes(SystemCode.SUCCESS);
//                result.setObj(user);
//            }
//        }
        return result;
    }

    /**
     * @param request
     * @param user    登录
     * @return adminName, password
     */
    //@GetMapping("/toLogin")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},value = "/toLogin")
    private JsonResponse<User> toLogin(HttpServletRequest request, User user) {
        JsonResponse<User> result = new JsonResponse<User>(SystemCode.FAILURE);
        User model = userService.selectByPrimaryKey(user.getUserId());
        if (model != null) {
            // 登录成功
            SessionUtil.setUser(request, model);
//            request.getSession().setAttribute("adaYangphone", model.getPhone());
            result.setRes(SystemCode.SUCCESS);

        }

        return result;
    }

    /**
     * @param request
     * @return adminName, password
     */

    //@GetMapping("/test")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},value = "/test")
    public JsonResponse<String> test(HttpServletRequest request) {
        JsonResponse<String> result = new JsonResponse<String>(
                SystemCode.FAILURE);
        User user = SessionUtil.getUser(request);
        if (null == user) {
            result.setRes(SystemCode.NO_LOGIN);
            return result;
        } else {
            result.setRes(SystemCode.SUCCESS);
        }
        return result;
    }

    /**
     * @param request
     * @return adminName, password
     */
    //@GetMapping("/logout")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},value = "/logout")
    public JsonResponse<User> logout(HttpServletRequest request) {
        JsonResponse<User> result = new JsonResponse<User>(SystemCode.FAILURE);
        User user = SessionUtil.getUser(request);
        if (null != user) {
            request.getSession().invalidate();
        }
        result.setRes(SystemCode.SUCCESS);
        return result;
    }


}
