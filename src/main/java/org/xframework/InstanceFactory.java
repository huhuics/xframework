/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.xframework.core.ClassScanner;
import org.xframework.core.ConfigHelper;
import org.xframework.core.impl.DefaultClassScanner;
import org.xframework.mvc.HandlerExceptionResolver;
import org.xframework.mvc.HandlerInvoker;
import org.xframework.mvc.HandlerMapping;
import org.xframework.mvc.ViewResolver;
import org.xframework.mvc.impl.DefaultHandlerExceptionResolver;
import org.xframework.mvc.impl.DefaultHandlerInvoker;
import org.xframework.mvc.impl.DefaultHandlerMapping;
import org.xframework.mvc.impl.DefaultViewResolver;
import org.xframework.util.ObjectUtil;

/**
 * 实例工厂
 * @author HuHui
 * @version $Id: InstanceFactory.java, v 0.1 2017年4月8日 上午9:06:08 HuHui Exp $
 */
public class InstanceFactory {

    /** 缓存实例的map */
    private static final Map<String, Object> cache                      = new ConcurrentHashMap<String, Object>();

    /***************定义key****************/

    /** ClassScanner */
    private static final String              CLASS_SCANNER              = "xframe.class_scanner";

    /** DataSourceFactory */
    private static final String              DS_FACTORY                 = "xframe.ds_factory";

    /** HandlerMapping */
    private static final String              HANDLER_MAPPING            = "xframe.handler_mapping";

    /** ViewResolver */
    private static final String              VIEW_RESOLVER              = "xframe.view_resolver";

    /** HandlerInvoker */
    private static final String              HANDLER_INVOKER            = "xframe.handler_invoker";

    /** HandlerExceptionResolver */
    private static final String              HANDLER_EXCEPTION_RESOLVER = "xframe.handler_exception_resolver";

    /**
     * 获取ClassScanner
     */
    public static ClassScanner getClassScanner() {
        return getInstance(CLASS_SCANNER, DefaultClassScanner.class);
    }

    /**
     * 获取HandlerMapping
     */
    public static HandlerMapping getHandlerMapping() {
        return getInstance(HANDLER_MAPPING, DefaultHandlerMapping.class);
    }

    /**
     * 获取ViewResolver
     */
    public static ViewResolver getViewResolver() {
        return getInstance(VIEW_RESOLVER, DefaultViewResolver.class);
    }

    /**
     * 获取HandlerInvoker
     */
    public static HandlerInvoker getHandlerInvoker() {
        return getInstance(HANDLER_INVOKER, DefaultHandlerInvoker.class);
    }

    /**
     * 获取HandlerExceptionResolver
     */
    public static HandlerExceptionResolver getHandlerExceptionResolver() {
        return getInstance(HANDLER_EXCEPTION_RESOLVER, DefaultHandlerExceptionResolver.class);
    }

    /**
     * 获取类实例,如果配置文件中没有配置相关类,则使用默认实现类
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(String cacheKey, Class<T> defaultImplClass) {
        //若缓存中存在对应的实例,则返回该实例
        if (cache.containsKey(cacheKey)) {
            return (T) cache.get(cacheKey);
        }
        //从配置文件中读取相应接口实现类的配置
        String implClassName = ConfigHelper.getString(cacheKey);
        //若实现类配置不存在,则使用默认实现类
        if (StringUtils.isEmpty(implClassName)) {
            implClassName = defaultImplClass.getName();
        }
        //通过反射创建类对应的实例
        T instance = ObjectUtil.newInstance(implClassName);
        //如果实例创建成功则放入缓存
        if (instance != null) {
            cache.put(cacheKey, instance);
        }
        //返回实例
        return instance;
    }
}
