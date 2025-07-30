/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xframework.FrameworkConstant;
import org.xframework.mvc.HandlerExceptionResolver;
import org.xframework.mvc.fault.AuthcException;
import org.xframework.mvc.fault.AuthzException;
import org.xframework.util.LogUtil;
import org.xframework.util.WebUtil;

/**
 * 默认Handler异常解析器
 * @author HuHui
 * @version $Id: DefaultHandlerExceptionResolver.java, v 0.1 2017年4月22日 上午11:21:55 HuHui Exp $
 */
public class DefaultHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(DefaultHandlerExceptionResolver.class);

    @Override
    public void resolveHandlerException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        //判断异常原因
        Throwable cause = e.getCause();
        if (cause == null) {
            LogUtil.error(e, logger, e.getMessage());
            return;
        }
        if (cause instanceof AuthcException) {
            //分两种情况进行处理
            if (WebUtil.isAJAX(request)) {
                //跳转到403页面
                WebUtil.sendError(HttpServletResponse.SC_FORBIDDEN, "", response);
            } else {
                //重定向到首页
                WebUtil.redirectRequest(FrameworkConstant.HOME_PAGE, request, response);
            }
        } else if (cause instanceof AuthzException) {
            //跳转到403页面
            WebUtil.sendError(HttpServletResponse.SC_FORBIDDEN, "", response);
        } else {
            //跳转到500页面
            WebUtil.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, cause.getMessage(), response);
        }
    }
}
