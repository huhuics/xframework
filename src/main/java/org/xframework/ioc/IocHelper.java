/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.ioc;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.xframework.core.ClassHelper;
import org.xframework.ioc.annotation.Impl;
import org.xframework.ioc.annotation.Inject;

/**
 * 初始化IOC容器
 * @author HuHui
 * @version $Id: IocHelper.java, v 0.1 2017年4月17日 上午11:09:05 HuHui Exp $
 */
public class IocHelper {

    static {
        try {
            // 获取并遍历所有Bean类
            Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                // 获取Bean类和Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                // 获取Bean类所有字段(不包括父类中的方法)
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtils.isNotEmpty(beanFields)) {
                    // 遍历Bean的所有字段
                    for (Field beanField : beanFields) {
                        // 判断当前Bean字段是否带有Inject注解
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            // 获取Bean字段对应的接口
                            Class<?> interfaceClass = beanField.getType();
                            // 获取Bean字段对应的实现类
                            Class<?> implementClass = findImplementClass(interfaceClass);
                            // 若存在实现类,则执行以下代码
                            if (implementClass != null) {
                                // 从Bean Map中获取该实现类对应的实现类实例
                                Object implementInstance = beanMap.get(implementClass);
                                // 设置该Bean字段的值
                                if (implementInstance != null) {
                                    beanField.setAccessible(true);
                                    beanField.set(beanInstance, implementInstance); // 设置字段初始值
                                } else {
                                    throw new RuntimeException("依赖注入失败! 类名:" + beanClass.getSimpleName() + ", 字段名:" + interfaceClass.getSimpleName());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("初始化IocHelper出错!", e);
        }
    }

    private static Class<?> findImplementClass(Class<?> interfaceClass) {
        Class<?> implementClass = interfaceClass;
        // 判断接口上是否标注了Impl注解
        if (interfaceClass.isAnnotationPresent(Impl.class)) {
            // 获取强制指定的实现类
            implementClass = interfaceClass.getAnnotation(Impl.class).value();
        } else {
            // 获取该接口所有实现类
            List<Class<?>> implementClassList = ClassHelper.getClassListBySuper(interfaceClass);
            if (CollectionUtils.isNotEmpty(implementClassList)) {
                // 获取第一个实现类
                implementClass = implementClassList.get(0);
            }
        }

        // 返回实现类对象
        return implementClass;
    }
}
