/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.util;

/**
 * 对象操作工具类
 * @author HuHui
 * @version $Id: ObjectUtil.java, v 0.1 2017年4月11日 下午3:34:53 HuHui Exp $
 */
public final class ObjectUtil {

    /**
     * 通过反射创建实例
     * @param className 类的全路径名称
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className) {
        T instance;
        try {
            Class<?> commandClass = ClassUtil.loadClass(className);
            instance = (T) commandClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("创建类实例出错", e);
        }
        return instance;
    }

}
