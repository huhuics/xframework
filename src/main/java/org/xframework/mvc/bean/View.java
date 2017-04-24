/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc.bean;

import java.util.HashMap;
import java.util.Map;

import org.xframework.core.bean.BaseBean;

/**
 * 视图对象
 * @author HuHui
 * @version $Id: View.java, v 0.1 2017年4月20日 下午8:00:42 HuHui Exp $
 */
public class View extends BaseBean {

    /**  */
    private static final long   serialVersionUID = -8273370854284461270L;

    /** 视图路径 */
    private String              path;

    /** 相关数据 */
    private Map<String, Object> data;

    public View(String path) {
        this.path = path;
        data = new HashMap<String, Object>();
    }

    public View data(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public boolean isRedirect() {
        return path.startsWith("/");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
