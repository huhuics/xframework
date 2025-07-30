/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.ds.impl;

import javax.sql.DataSource;

import org.xframework.core.ConfigHelper;
import org.xframework.ds.DataSourceFactory;

/**
 * 抽象数据源工厂
 * @author HuHui
 * @version $Id: AbstractDataSourceFactory.java, v 0.1 2017年5月1日 下午2:19:47 HuHui Exp $
 */
public abstract class AbstractDataSourceFactory<T extends DataSource> implements DataSourceFactory {

    protected final String driver   = ConfigHelper.getString("xframe.jdbc.driver");

    protected final String url      = ConfigHelper.getString("xframe.jdbc.url");

    protected final String username = ConfigHelper.getString("xframe.jdbc.username");

    protected final String password = ConfigHelper.getString("xframe.jdbc.password");

    @Override
    public T getDataSource() {
        //创建数据源
        T ds = createDataSource();
        //设置基础属性
        setDriver(ds, driver);
        setUrl(ds, url);
        setUsername(ds, username);
        setPassword(ds, password);
        //设置高级属性
        setAdvancedConfig(ds);
        return ds;
    }

    public abstract T createDataSource();

    public abstract void setDriver(T ds, String driver);

    public abstract void setUrl(T ds, String url);

    public abstract void setUsername(T ds, String username);

    public abstract void setPassword(T ds, String password);

    public abstract void setAdvancedConfig(T ds);

}
