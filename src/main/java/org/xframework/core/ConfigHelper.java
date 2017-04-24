/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.core;

import java.util.Properties;

import org.xframework.FrameworkConstant;
import org.xframework.util.PropsUtil;

/**
 * 获取属性文件中的属性值
 * @author HuHui
 * @version $Id: ConfigHelper.java, v 0.1 2017年4月7日 上午11:42:50 HuHui Exp $
 */
public final class ConfigHelper {

    /** 加载配置文件 */
    private static final Properties configProps = PropsUtil.loadProps(FrameworkConstant.CONFIG_PROPS);

    /**
     * 获取String类型属性值
     */
    public static String getString(String key) {
        return PropsUtil.getString(configProps, key);
    }

    /**
     * 获取String类型属性值(带默认值)
     */
    public static String getString(String key, String defaultValue) {
        return PropsUtil.getString(configProps, key, defaultValue);
    }

    /**
     * 获取int类型属性值(带默认值)
     */
    public static int getInt(String key, int defaultValue) {
        return PropsUtil.getNumber(configProps, key, defaultValue);
    }

}
