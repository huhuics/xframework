/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handler调用器
 * @author HuHui
 * @version $Id: HandlerInvoker.java, v 0.1 2017年4月20日 下午9:09:10 HuHui Exp $
 */
public interface HandlerInvoker {

    /**
     * 调用Handler
     * @param request    Http请求对象
     * @param response   Http响应对象
     * @param handler    Handler
     * @throws Exception 异常
     */
    void invokeHandler(HttpServletRequest request, HttpServletResponse response, Handler handler) throws Exception;

}
