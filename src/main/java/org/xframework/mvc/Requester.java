/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc;

/**
 * 封装请求方法与请求路径
 * @author HuHui
 * @version $Id: Requester.java, v 0.1 2017年4月19日 下午5:59:35 HuHui Exp $
 */
public class Requester {

    /**
     * 请求方法 
     * GET, POST, PUT, DELETE
     */
    private String requestMethod;

    /** 请求路径 */
    private String requestPath;

    public Requester(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

}
