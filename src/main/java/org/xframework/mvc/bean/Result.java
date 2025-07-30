/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc.bean;

import org.xframework.core.bean.BaseBean;

/**
 * 封装返回数据
 * @author HuHui
 * @version $Id: Result.java, v 0.1 2017年4月20日 下午8:12:25 HuHui Exp $
 */
public class Result extends BaseBean {

    /**  */
    private static final long serialVersionUID = 507055537739131217L;

    /** 成功标识 */
    private boolean           success;

    /** 错误代码 */
    private int               error;

    /** 返回的相关数据 */
    private Object            data;

    public Result(boolean success) {
        this.success = success;
    }

    public Result error(int error) {
        this.error = error;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
