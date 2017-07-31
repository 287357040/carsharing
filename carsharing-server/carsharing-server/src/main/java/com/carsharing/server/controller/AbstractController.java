package com.carsharing.server.controller;

import com.carsharing.server.util.SessionUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
