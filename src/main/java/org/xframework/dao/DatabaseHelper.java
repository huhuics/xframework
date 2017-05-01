/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xframework.InstanceFactory;
import org.xframework.ds.DataSourceFactory;

/**
 * 封装数据库相关操作
 * @author HuHui
 * @version $Id: DatabaseHelper.java, v 0.1 2017年5月1日 下午2:00:48 HuHui Exp $
 */
public class DatabaseHelper {

    private static final Logger                  logger            = LoggerFactory.getLogger(DatabaseHelper.class);

    /**
     * 定义一个局部线程变量(使每个线程都拥有自己的连接)
     */
    private static final ThreadLocal<Connection> connContainer     = new ThreadLocal<Connection>();

    /**
     * 获取数据源工厂
     */
    private static final DataSourceFactory       dataSourceFactory = InstanceFactory.getDataSourceFactory();

    /**
     * 获取数据源
     */
    public static DataSource getDataSource() {
        return dataSourceFactory.getDataSource();
    }
}
