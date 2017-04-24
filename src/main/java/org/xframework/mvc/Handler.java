/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc;

import java.lang.reflect.Method;
import java.util.regex.Matcher;

/**
 * 封装Action方法相关信息
 * @author HuHui
 * @version $Id: Handler.java, v 0.1 2017年4月19日 下午6:04:06 HuHui Exp $
 */
public class Handler {

    private Class<?> actionClass;

    private Method   actionMethod;

    private Matcher  requestPathMatcher;

    public Handler(Class<?> actionClass, Method actionMethod) {
        this.actionClass = actionClass;
        this.actionMethod = actionMethod;
    }

    public Matcher getRequestPathMatcher() {
        return requestPathMatcher;
    }

    public void setRequestPathMatcher(Matcher requestPathMatcher) {
        this.requestPathMatcher = requestPathMatcher;
    }

    public Class<?> getActionClass() {
        return actionClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

}
