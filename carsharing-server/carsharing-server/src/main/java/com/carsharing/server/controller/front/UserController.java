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
import org.springframework.web.bind.annotation.*;

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
//    @SendTo("/topic/callback")
    public JsonResponse<User> loginByCode(User frmUser,
                                          HttpServletRequest request) {
            JsonResponse<User> result = new JsonResponse<User>(
                SystemCode.FAILURE);
        //
        // 检验验证码 模拟环境，验证通过
        result.setRes(SystemCode.SUCCESS);
        try {
            if (result.getRes() == SystemCode.SUCCESS) {
                // by yucs
                if (SessionUtil.getUser(request) == null) {
                    lo.info("用户为空...");
                    User user = userService.selectByPrimaryKey(frmUser.getUserNo());
                    if (user == null) {
                        User model = new User();
                        model.setIsFirst(true);
                        model.setUserNo(frmUser.getUserNo());
                        model.setMobile(frmUser.getMobile());
                        model.setCreateTime(new Date());
                        model.setUpdateTime(new Date());
                        userService.insertSelective(model);
                        model = userService.getOneByPhone(frmUser.getMobile());
                        SessionUtil.setUser(request, model);
                        result.setObj(model);
                    } else {
                        user.setIsFirst(false);
                        SessionUtil.setUser(request, user);
                        result.setObj(user);
                    }
                    result.setRes(SystemCode.SUCCESS);
                } else {
                    lo.info("用户不为空..."
                            + SessionUtil.getUser(request).getUserNo());
                    User user = userService.selectByPrimaryKey(SessionUtil
                            .getUser(request).getUserNo());
                    user.setMobile(user.getMobile());
                    userService.updateByPrimaryKeySelective(user);
                    SessionUtil.setUser(request, user);
                    result.setObj(user);
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
     * 根据账号密码登录
     *
     * @param request
     * @param frmUser
     * @return adminName, password
     */
    //@GetMapping("/toLogin")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/loginByAcct")
    private JsonResponse<User> toLogin(HttpServletRequest request, User frmUser) {
        JsonResponse<User> result = new JsonResponse<User>(SystemCode.FAILURE);

        User model = userService.selectByPrimaryKey(frmUser.getUserNo());
        if (null == model) {
            result.setRes(SystemCode.NO_OBJ_ERROR_PASS);
            return result;
        } else if (!model.getPassword().equals(frmUser.getPassword())) {
            result.setRes(SystemCode.WRONG_PASSWORD);
            return result;
        }
        SessionUtil.setUser(request, frmUser);
        result.setRes(SystemCode.SUCCESS);
        result.setObj(model);
        return result;
    }

    /**
     * @param request
     * @return
     */
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
    @RequestMapping(method = {RequestMethod.POST}, value = "/updateUserInfo")
    public JsonResponse<String> setUserInfo(User user, HttpServletRequest request) {

        JsonResponse<String> result = new JsonResponse<String>(SystemCode.FAILURE);
        User baseUser = SessionUtil.getUser(request);
        if (null != user.getBirth())
            baseUser.setBirth(user.getBirth());
        if (null != user.getUserName())
            baseUser.setUserName(user.getUserName());
        if (null != user.getMobile())
            baseUser.setMobile(user.getMobile());
        if (null != user.getDepartment())
            baseUser.setDepartment(user.getDepartment());
        if (null != user.getPassword())
            baseUser.setPassword(user.getPassword());

        baseUser.setSex(user.getSex());
        baseUser.setUpdateTime(new Date());

        try {
            userService.updateByPrimaryKeySelective(baseUser);
            result.setRes(SystemCode.SUCCESS);
        } catch (Exception e) {
            lo.error("修改用户信息失败", e);
            logError(request, "[修改用户信息失败]", e);
        }
        return result;
    }

    /**
     * @param password
     * @param request
     * @return String
     * 用户登录后，设置用户信息
     */
    @RequestMapping(method = {RequestMethod.POST}, value = "/changePassword")
    public JsonResponse<String> changePassword(@RequestParam("password") String password, HttpServletRequest request) {

        JsonResponse<String> result = new JsonResponse<String>(SystemCode.FAILURE);
        User baseUser = SessionUtil.getUser(request);

        User serveUser = userService.selectByPrimaryKey(baseUser.getUserNo());
        serveUser.setPassword(password);
        try {
            userService.updateByPrimaryKeySelective(serveUser);
            result.setRes(SystemCode.SUCCESS);
        } catch (Exception e) {
            lo.error("修改密码失败", e);
            logError(request, "[修改密码失败]", e);
        }

        return result;
    }

    /**
     * 获取用户详情
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/getUserByNo")
    public JsonResponse<User> getUserInfo(HttpServletRequest request) {
        JsonResponse<User> result = new JsonResponse<User>(SystemCode.FAILURE);
        lo.info("getUserByNo...");
        User user = SessionUtil.getUser(request);
        if (null == user) {
            result.setRes(SystemCode.NO_LOGIN);
            return result;
        } else {
            user = userService.selectByPrimaryKey(user.getUserNo());
            if (user != null) {
                result.setRes(SystemCode.SUCCESS);
                result.setObj(user);
            }
        }
        return result;
    }


    /**
     * 增加朋友
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/addFriend")
    public JsonResponse<String> addFriend() {

        JsonResponse<String> result = new JsonResponse<String>();

        return result;
    }


}
