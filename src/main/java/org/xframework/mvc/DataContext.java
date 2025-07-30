/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 数据上下文
 * @author HuHui
 * @version $Id: DataContext.java, v 0.1 2017年4月22日 下午8:24:21 HuHui Exp $
 */
public class DataContext {

    /** 使每个线程拥有各自的DataContext实例 */
    private static final ThreadLocal<DataContext> dataContextContainer = new ThreadLocal<DataContext>();

    private HttpServletRequest                    request;

    private HttpServletResponse                   response;

    public static void init(HttpServletRequest request, HttpServletResponse response) {
        DataContext dataContext = new DataContext();
        dataContext.request = request;
        dataContext.response = response;
        dataContextContainer.set(dataContext);
    }

    /**
     * 销毁
     */
    public static void destory() {
        dataContextContainer.remove();
    }

}
