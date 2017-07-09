package com.carsharing.server.controller;

import com.carsharing.server.util.SessionUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共controller
 */
public abstract class AbstractController {
    /**
     * 日志
     */
    protected Logger logger = Logger.getLogger(this.getClass());

    /**
     * 打印日志
     */
    protected void logInfo(HttpServletRequest request, String msg) {
        logger.info(SessionUtil.getLogHead(request) + msg);
    }

    protected void logError(HttpServletRequest request, String msg, Throwable e) {
        logger.error(SessionUtil.getLogHead(request) + msg, e);
    }

    protected void logError(String msg, Throwable e) {
        logger.error(msg, e);
    }
}
