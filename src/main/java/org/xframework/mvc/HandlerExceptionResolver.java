/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handler异常解析器
 * @author HuHui
 * @version $Id: HandlerExceptionResolver.java, v 0.1 2017年4月22日 上午11:19:48 HuHui Exp $
 */
public interface HandlerExceptionResolver {

    /**
     * 解析Handler异常
     */
    void resolveHandlerException(HttpServletRequest request, HttpServletResponse response, Exception e);

}
