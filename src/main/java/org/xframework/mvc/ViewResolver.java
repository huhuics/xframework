/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视图解析器
 * @author HuHui
 * @version $Id: ViewResolver.java, v 0.1 2017年4月20日 下午9:13:14 HuHui Exp $
 */
public interface ViewResolver {

    /**
     * 解析视图
     * @param request            Http请求对象
     * @param response           Http响应对象
     * @param actionMethodResult Action方法返回值
     */
    void resolveView(HttpServletRequest request, HttpServletResponse response, Object actionMethodResult);

}
