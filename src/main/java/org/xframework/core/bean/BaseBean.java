/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.core.bean;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 提供Bean类的基础特性
 * @author HuHui
 * @version $Id: BaseBean.java, v 0.1 2017年4月20日 下午7:52:04 HuHui Exp $
 */
public class BaseBean implements Serializable {

    /**  */
    private static final long serialVersionUID = 7968739605127810774L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
