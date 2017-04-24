/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import org.apache.commons.lang3.StringUtils;
import org.xframework.FrameworkConstant;
import org.xframework.HelperLoader;

/**
 * 容器监听器
 * @author HuHui
 * @version $Id: ContainerListener.java, v 0.1 2017年4月24日 上午9:59:59 HuHui Exp $
 */
@WebListener
public class ContainerListener implements ServletContextListener {

    /**
     * 当容器初始化时调用
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        //获取ServletContext
        ServletContext servletContext = event.getServletContext();
        //初始化相关Helper类
        HelperLoader.init();
        //添加Servlet映射
        addServletMapping(servletContext);
        //TODO 注册WebPlugin
    }

    /**
     * 当容器销毁时调用
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

    private void addServletMapping(ServletContext context) {
        //使用DefaultServlet映射所有静态资源
        registerDefaultServlet(context);
        //使用JSPServlet映射所有jsp请求
        registerJspServlet(context);
    }

    private void registerDefaultServlet(ServletContext context) {
        ServletRegistration defaultServlet = context.getServletRegistration("default");
        defaultServlet.addMapping("/index.html");
        defaultServlet.addMapping("/favicon.ico");
        String wwwPath = FrameworkConstant.WWW_PATH;
        if (StringUtils.isNotEmpty(wwwPath)) {
            defaultServlet.addMapping(wwwPath + "*");
        }
    }

    private void registerJspServlet(ServletContext context) {
        ServletRegistration jspServlet = context.getServletRegistration("jsp");
        jspServlet.addMapping("/index.jsp");
        String jspPath = FrameworkConstant.JSP_PATH;
        if (StringUtils.isNotEmpty(jspPath)) {
            jspServlet.addMapping(jspPath + "*");
        }
    }

}
