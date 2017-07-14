package com.carsharing.server.util;

import com.carsharing.server.entity.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 从request，session中获取信息
 */
public class SessionUtil {
    /**
     * 从request中获取后端用户
     *
     * @param request request
     * @param return  user 登入后存入session的对象
     */
    public static Admin getAdminUser(HttpServletRequest request) {
        return (Admin) request.getSession().getAttribute("adminUser");
    }

    public static void setAdminUser(HttpServletRequest request, Admin admin) {
        request.getSession().setAttribute("adminUser", admin);
    }


    public static void setUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute("user", user);
    }

    /**
     * 从session中获取前端用户
     *
     */
    public static User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    /**
     * 获取访问者IP
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request.getRemoteAddr()。
     *
     * @param request
     * @return
     */
    public static String getLogHead(HttpServletRequest request) {
        return "ip为 " + getIpAddr(request) + " ";
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
}
