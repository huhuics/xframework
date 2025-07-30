/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc.impl;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xframework.mvc.ActionHelper;
import org.xframework.mvc.Handler;
import org.xframework.mvc.HandlerMapping;
import org.xframework.mvc.Requester;

/**
 * 默认映射处理器
 * @author HuHui
 * @version $Id: DefaultHandlerMapping.java, v 0.1 2017年4月20日 下午8:35:45 HuHui Exp $
 */
public class DefaultHandlerMapping implements HandlerMapping {

    @Override
    public Handler getHandler(String currentRequestMethod, String currentRequestPath) {
        // 定义一个Handler
        Handler handler = null;
        // 获取并遍历Action映射
        Map<Requester, Handler> actionMap = ActionHelper.getActionMap();
        for (Map.Entry<Requester, Handler> actionEntry : actionMap.entrySet()) {
            //从Requester中获取Request相关属性
            Requester requester = actionEntry.getKey();
            String requestMethod = requester.getRequestMethod();//GET, POST
            String requestPath = requester.getRequestPath();
            //获取请求路径匹配器(使用正则表达式匹配请求路径并从中获取相应的请求参数)
            Matcher requestPathMatcher = Pattern.compile(requestPath).matcher(currentRequestPath);
            //判断请求方法与请求路径是否同时匹配
            if (requestMethod.equalsIgnoreCase(currentRequestMethod) && requestPathMatcher.matches()) {
                //获取Handler及其相关属性
                handler = actionEntry.getValue();
                //设置请求路径匹配器
                if (handler != null) {
                    handler.setRequestPathMatcher(requestPathMatcher);
                }
                //若匹配成功,则终止循环
                break;
            }
        }
        return handler;
    }
}
