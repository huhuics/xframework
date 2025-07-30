/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc;

/**
 * 映射处理器
 * @author HuHui
 * @version $Id: HandlerMapping.java, v 0.1 2017年4月20日 下午8:31:34 HuHui Exp $
 */
public interface HandlerMapping {

    /**
     * 获取Handler
     * @param currentRequestMethod  当前请求方法
     * @param currentRequestPath    当前请求路径
     * @return
     */
    Handler getHandler(String currentRequestMethod, String currentRequestPath);

}
