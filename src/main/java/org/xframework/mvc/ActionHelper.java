/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.xframework.core.ClassHelper;
import org.xframework.mvc.annotation.Action;
import org.xframework.mvc.annotation.Request;
import org.xframework.util.StringUtil;

/**
 * 初始化Action配置
 * @author HuHui
 * @version $Id: ActionHelper.java, v 0.1 2017年4月19日 下午6:12:25 HuHui Exp $
 */
public class ActionHelper {

    /** Action Map (HTTP请求与Action方法的映射) */
    private static final Map<Requester, Handler> actionMap = new LinkedHashMap<Requester, Handler>();

    static {
        // 获取所有Action类
        List<Class<?>> actionClassList = ClassHelper.getClassListByAnnotation(Action.class);
        if (CollectionUtils.isNotEmpty(actionClassList)) {
            // 定义两个Action Map
            Map<Requester, Handler> commonActionMap = new HashMap<Requester, Handler>(); // 存放普通Action的Map
            Map<Requester, Handler> regexpActionMap = new HashMap<Requester, Handler>(); // 存放带有正则表达式Action的Map
            // 遍历Action类
            for (Class<?> actionClass : actionClassList) {
                // 获取并遍历该Action类中所有方法
                Method[] actionMethods = actionClass.getDeclaredMethods();
                if (ArrayUtils.isNotEmpty(actionMethods)) {
                    for (Method actionMethod : actionMethods) {
                        //处理Action方法
                        handleActionMethod(actionClass, actionMethod, commonActionMap, regexpActionMap);
                    }
                }
            }
            // 初始化最终的Action Map(将Common放在Regexp前面)
            actionMap.putAll(commonActionMap);
            actionMap.putAll(regexpActionMap);
        }
    }

    private static void handleActionMethod(Class<?> actionClass, Method actionMethod, Map<Requester, Handler> commonActionMap,
                                           Map<Requester, Handler> regexpActionMap) {
        // 判断当前Action方法是否带有Request注解
        if (actionMethod.isAnnotationPresent(Request.Get.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Get.class).value();
            putActionMap("GET", requestPath, actionClass, actionMethod, commonActionMap, regexpActionMap);
        } else if (actionMethod.isAnnotationPresent(Request.Post.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Post.class).value();
            putActionMap("POST", requestPath, actionClass, actionMethod, commonActionMap, regexpActionMap);
        } else if (actionMethod.isAnnotationPresent(Request.Put.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Put.class).value();
            putActionMap("PUT", requestPath, actionClass, actionMethod, commonActionMap, regexpActionMap);
        } else if (actionMethod.isAnnotationPresent(Request.Delete.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Delete.class).value();
            putActionMap("DELETE", requestPath, actionClass, actionMethod, commonActionMap, regexpActionMap);
        }
    }

    private static void putActionMap(String requestMethod, String requestPath, Class<?> actionClass, Method actionMethod,
                                     Map<Requester, Handler> commonActionMap, Map<Requester, Handler> regexpActionMap) {
        // 判断Request Path中是否带有占位符
        if (requestPath.matches(".+\\{\\w+\\}.*")) {
            // 将请求路径中的占位符{\w+}转换为正则表达式(\\w+)
            requestPath = StringUtil.replaceAll(requestPath, "\\{\\w+\\}", "(\\\\w+)");
            // 将Requester与Handler放入regexpActionMap
            regexpActionMap.put(new Requester(requestMethod, requestPath), new Handler(actionClass, actionMethod));
        } else {
            // 将Requester与Handler放入commonActionMap
            commonActionMap.put(new Requester(requestMethod, requestPath), new Handler(actionClass, actionMethod));
        }
    }

    /**
     * 获取Action Map
     */
    public static Map<Requester, Handler> getActionMap() {
        return actionMap;
    }

}
