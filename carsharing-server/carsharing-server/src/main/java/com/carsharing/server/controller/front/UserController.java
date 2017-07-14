package com.carsharing.server.controller.front;

import com.carsharing.server.constant.SystemCode;
import com.carsharing.server.controller.AbstractController;
import com.carsharing.server.entity.User;
import com.carsharing.server.service.UserService;
import com.carsharing.server.util.JsonResponse;
import com.carsharing.server.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/front/user")
public class UserController extends AbstractController {
    private Logger lo = Logger.getLogger(UserController.class);

    @Resource
    private UserService userService;

    /*
    * 通过手机验证码的方式登录
    * */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/loginByCode")
    @SendTo("/topic/callback")
    public JsonResponse<String> loginByCode(String userId, String mobile, Integer code,
                                            HttpServletRequest request) {
        JsonResponse<String> result = new JsonResponse<String>(
                SystemCode.FAILURE);
        // 检验验证码
        // 模拟环境，1234则验证通过
        if (1234 != code) {
            result.setMsg("验证码错误!");
            return result;
        }
        result.setRes(SystemCode.SUCCESS);
        try {
            if (result.getRes() == SystemCode.SUCCESS) {
                // by yucs
                if (SessionUtil.getUser(request) == null) {
                    lo.info("用户为空...");
                    User user = userService.getOneByPhone(mobile);
                    if (user == null) {
                        User model = new User();
                        model.setUserNum(userId);
                        model.setMobile(mobile);
                        model.setCreateTime(new Date());
                        model.setUpdateTime(new Date());
                        model.setUserName("Jim");
                        userService.insertSelective(model);
                        model = userService.getOneByPhone(mobile);
                        SessionUtil.setUser(request, model);
                    } else {
                        SessionUtil.setUser(request, user);
                    }
                    result.setRes(SystemCode.SUCCESS);
                } else {
                    lo.info("用户不为空..."
                            + SessionUtil.getUser(request).getUserNum());
                    User user = userService.selectByPrimaryKey(SessionUtil
                            .getUser(request).getUserNum());
                    user.setMobile(mobile);
                    userService.updateByPrimaryKeySelective(user);
                    SessionUtil.setUser(request, user);
                }
                result.setRes(SystemCode.SUCCESS);
            }

        } catch (Exception e) {
            lo.error("更新用户失败", e);
            logError(request, "[修改用户手机失败]", e);
        }

        return result;
    }
    /**
     * @param request
     * @param user    根据账号密码登录
     * @return adminName, password
     */
    //@GetMapping("/toLogin")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/loginByAcct")
    private JsonResponse<User> toLogin(HttpServletRequest request, User user) {
        JsonResponse<User> result = new JsonResponse<User>(SystemCode.FAILURE);
//        User model = userService.selectByPrimaryKey(user.getUserNum());
//        if (model != null) {
//            // 登录成功
//            SessionUtil.setUser(request, model);
////            request.getSession().setAttribute("adaYangphone", model.getPhone());
//            result.setRes(SystemCode.SUCCESS);
//
//        }
//
        return result;
    }

    /**
     * @param request
     * @return adminName, password
     */
    //@GetMapping("/logout")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/logout")
    public JsonResponse<User> logout(HttpServletRequest request) {
        JsonResponse<User> result = new JsonResponse<User>(SystemCode.FAILURE);
        User user = SessionUtil.getUser(request);
        if (null != user) {
            request.getSession().invalidate();
        }
        result.setRes(SystemCode.SUCCESS);
        return result;
    }

    /**
     * @param user
     * @param request
     * @return String
     * 用户登录后，设置用户信息
     */
    @RequestMapping(method = {RequestMethod.POST}, value = "/setUserInfo")
    public JsonResponse<String> setUserInfo(User user,HttpServletRequest request){
        JsonResponse<String> result = new JsonResponse<String>(
                SystemCode.FAILURE);
        return result;
    }

    /**
     * 获取用户详情
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/getUserByNo")
    public JsonResponse<User> getUserByNo(String userNum,
                                          HttpServletRequest request) {
        JsonResponse<User> result = new JsonResponse<User>(SystemCode.FAILURE);
        lo.info("getUserByNo...");
        User user = SessionUtil.getUser(request);
        if (null == user) {
            result.setRes(SystemCode.NO_LOGIN);
            return result;
        } else {
            user = userService.selectByPrimaryKey(user.getUserNum());
            if (user != null) {
                result.setRes(SystemCode.SUCCESS);
                result.setObj(user);
            }
        }
        return result;
    }


    /**
     *
     * 发布乘车需求
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value="/publishRideRequirement")
    public JsonResponse<String> publishRideRequirement(){

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }

    /**
     * 增加评论
     *
     */

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value="/addComment")
    public JsonResponse<String> addComment(){

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }

}
