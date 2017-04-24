/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

/**
 * 读取属性文件工具类
 * @author HuHui
 * @version $Id: PropsUtil.java, v 0.1 2017年4月7日 下午12:51:35 HuHui Exp $
 */
public class PropsUtil {

    private static final String PROPS_SUFFIX = ".properties";

    /**
     * 加载属性文件
     */
    public static Properties loadProps(String propsPath) {
        Properties props = new Properties();
        InputStream is = null;

        if (StringUtils.isEmpty(propsPath)) {
            throw new IllegalArgumentException("配置文件路径不能为空");
        }

        if (propsPath.lastIndexOf(PROPS_SUFFIX) == -1) {
            propsPath += PROPS_SUFFIX;
        }

        is = ClassUtil.getClassLoader().getResourceAsStream(propsPath);
        if (is != null) {
            try {
                props.load(is);
            } catch (IOException e) {
                throw new RuntimeException("读取配置文件异常", e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException("释放资源异常", e);
                }
            }
        }

        return props;
    }

    /**
     * 加载配置文件,并转换成Map
     */
    public static Map<String, String> loadPropsToMap(String propsPath) {
        Map<String, String> map = new HashMap<String, String>();
        Properties props = loadProps(propsPath);
        for (String key : props.stringPropertyNames()) {
            map.put(key, props.getProperty(key));
        }
        return map;
    }

    /**
     * 获取字符型属性值
     */
    public static String getString(Properties props, String key) {
        String value = "";
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * 获取字符型属性值(带默认值)
     */
    public static String getString(Properties props, String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    /**
     * 获取整数型属性值(带默认值)
     */
    public static int getNumber(Properties props, String key, int defaultValue) {
        return CastUtil.castInt(props.getProperty(key, defaultValue + ""), defaultValue);
    }
}
