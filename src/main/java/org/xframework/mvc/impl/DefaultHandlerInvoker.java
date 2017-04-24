/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;
import org.xframework.InstanceFactory;
import org.xframework.ioc.BeanHelper;
import org.xframework.mvc.Handler;
import org.xframework.mvc.HandlerInvoker;
import org.xframework.mvc.UploadHelper;
import org.xframework.mvc.ViewResolver;
import org.xframework.mvc.bean.Params;
import org.xframework.util.CastUtil;
import org.xframework.util.ClassUtil;
import org.xframework.util.WebUtil;

/**
 * 默认Handler调用器
 * @author HuHui
 * @version $Id: DefaultHandlerInvoker.java, v 0.1 2017年4月20日 下午9:10:15 HuHui Exp $
 */
public class DefaultHandlerInvoker implements HandlerInvoker {

    private ViewResolver viewResolver = InstanceFactory.getViewResolver();

    @Override
    public void invokeHandler(HttpServletRequest request, HttpServletResponse response, Handler handler) throws Exception {
        //获取Action相关信息
        Class<?> actionClass = handler.getActionClass();
        Method actionMethod = handler.getActionMethod();
        //获取Action实例
        Object actionInstance = BeanHelper.getBean(actionClass);
        //创建Action方法的参数列表
        List<Object> actionMethodParamList = createActionMethodParamList(request, handler);
        //检查参数列表个数是否合法
        checkParamList(actionMethod, actionMethodParamList);
        //调用Action方法
        Object actionMethodResult = invokeActionMethod(actionMethod, actionInstance, actionMethodParamList);
        //解析视图
        viewResolver.resolveView(request, response, actionMethodResult);
    }

    /**
     * 创建Action方法的参数列表
     */
    public List<Object> createActionMethodParamList(HttpServletRequest request, Handler handler) throws Exception {
        //定义参数列表
        List<Object> paramList = new ArrayList<Object>();
        //获取Action方法参数类型
        Class<?>[] actionParamTypes = handler.getActionMethod().getParameterTypes();
        //添加路径参数列表,处理请求路径中带占位符的参数
        paramList.addAll(createPathParamList(handler.getRequestPathMatcher(), actionParamTypes));
        //分两种情况处理
        if (UploadHelper.isMultipart(request)) {
            //TODO
        } else {
            //添加普通请求参数列表(包括Query String与Form Data)
            Map<String, Object> requestParamMap = WebUtil.getRequestParamMap(request);
            if (MapUtils.isNotEmpty(requestParamMap)) {
                paramList.add(new Params(requestParamMap));
            }
        }

        return paramList;
    }

    /**
     * 创建路径参数列表,处理请求路径中的带占位符参数
     */
    private List<Object> createPathParamList(Matcher requestPathMatcher, Class<?>[] actionParamTypes) {
        //定义参数列表
        List<Object> paramList = new ArrayList<Object>();
        //遍历正则表达式中所匹配的组 
        for (int i = 1; i <= requestPathMatcher.groupCount(); i++) {
            //获取请求参数
            String param = requestPathMatcher.group(i);
            //获取参数类型(支持四种类型: int/Integer, long/Long, double/Double, String)
            Class<?> paramType = actionParamTypes[i - 1];
            if (ClassUtil.isInt(paramType)) {
                paramList.add(CastUtil.castInt(param));
            } else if (ClassUtil.isLong(paramType)) {
                paramList.add(CastUtil.castLong(param));
            } else if (ClassUtil.isDouble(paramType)) {
                paramList.add(CastUtil.castDouble(param));
            } else if (ClassUtil.isString(paramType)) {
                paramList.add(param);
            }
        }
        //返回参数列表
        return paramList;
    }

    /**
     * 调用Action方法
     */
    private Object invokeActionMethod(Method actionMethod, Object actionInstance, List<Object> actionMethodParamList) throws Exception {
        //通过反射调用Action方法
        actionMethod.setAccessible(true);
        return actionMethod.invoke(actionInstance, actionMethodParamList.toArray());
    }

    /**
     * 参数校验
     */
    private void checkParamList(Method actionMethod, List<Object> actionMethodParamList) {
        //判断Action方法参数的个数是否匹配
        Class<?>[] actionMethodParameterTypes = actionMethod.getParameterTypes();
        if (actionMethodParameterTypes.length != actionMethodParamList.size()) {
            throw new RuntimeException("参数个数不匹配,无法调用Action方法!原始参数个数:" + actionMethodParameterTypes.length + ", 实际参数个数:" + actionMethodParamList.size());
        }
    }
}
