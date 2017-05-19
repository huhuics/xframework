/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc.bean;

import java.util.Map;

import org.xframework.core.bean.BaseBean;
import org.xframework.util.CastUtil;

/**
 * 封装HttpRequest请求参数
 * 通过参数名获取指定类型的参数值
 * @author HuHui
 * @version $Id: Params.java, v 0.1 2017年4月20日 下午7:54:49 HuHui Exp $
 */
public class Params extends BaseBean {

    /**  */
    private static final long         serialVersionUID = -5494328712975984604L;

    private final Map<String, Object> fieldMap;

    public Params(Map<String, Object> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public Map<String, Object> getFieldMap() {
        return fieldMap;
    }

    public String getString(String name) {
        return CastUtil.castString(get(name));
    }

    public double getDouble(String name) {
        return CastUtil.castDouble(get(name));
    }

    public long getLong(String name) {
        return CastUtil.castLong(get(name));
    }

    public int getInt(String name) {
        return CastUtil.castInt(get(name));
    }

    private Object get(String name) {
        return fieldMap.get(name);
    }

}
