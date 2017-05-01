/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.ds;

import javax.sql.DataSource;

/**
 * 数据源工厂
 * @author HuHui
 * @version $Id: DataSourceFactory.java, v 0.1 2017年5月1日 下午2:15:32 HuHui Exp $
 */
public interface DataSourceFactory {

    /**
     * 获取数据源
     */
    DataSource getDataSource();

}
