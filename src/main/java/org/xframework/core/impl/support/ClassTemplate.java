/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.core.impl.support;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xframework.util.ClassUtil;
import org.xframework.util.LogUtil;

/**
 * 用于获取类的模板类
 * @author HuHui
 * @version $Id: ClassTemplate.java, v 0.1 2017年4月8日 上午7:48:00 HuHui Exp $
 */
public abstract class ClassTemplate {

    private static final Logger logger = LoggerFactory.getLogger(ClassTemplate.class);

    protected final String      packageName;

    protected ClassTemplate(String packageName) {
        this.packageName = packageName;
    }

    public final List<Class<?>> getClassList() {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        try {
            //从包名获取URL类型的资源
            Enumeration<URL> urls = ClassUtil.getClassLoader().getResources(packageName.replace(".", "/"));
            //遍历URL资源
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                //获取协议名(分为file和jar)
                String protocol = url.getProtocol();
                if (protocol.equals("file")) {
                    //若在class目录中,则执行添加类操作
                    String packagePath = url.getPath().replaceAll("%20", " ");
                    addClass(classList, packagePath, packageName);
                } else if (protocol.equals("jar")) {
                    //若在jar包中,则解析jar包中的entry
                    JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                    JarFile jarFile = jarURLConnection.getJarFile();
                    Enumeration<JarEntry> jarEntries = jarFile.entries();
                    while (jarEntries.hasMoreElements()) {
                        JarEntry jarEntry = jarEntries.nextElement();
                        String jarEntryName = jarEntry.getName();
                        //判断该entry是否为class
                        if (jarEntryName.endsWith(".class")) {
                            //获取类名
                            String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                            //执行添加类操作
                            doAddClass(classList, className);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.error(e, logger, "获取类出错");
        }
        return classList;
    }

    private void addClass(List<Class<?>> classList, String packagePath, String packageName) {
        try {
            //获取包名路径下的class文件或目录
            File[] files = new File(packagePath).listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return (file.isFile() && file.getName().endsWith(".class")) || (file.isDirectory());
                }
            });
            //遍历文件或目录
            for (File file : files) {
                String fileName = file.getName();
                //判断是否为文件或目录
                if (file.isFile()) {
                    //获取类名
                    String className = fileName.substring(0, fileName.lastIndexOf("."));
                    if (StringUtils.isNotEmpty(packageName)) {
                        className = packageName + "." + className;
                    }
                    //执行添加类操作
                    doAddClass(classList, className);
                } else {
                    //获取子包路径
                    String subPackagePath = fileName;
                    if (StringUtils.isNotEmpty(packagePath)) {
                        subPackagePath = packagePath + "/" + subPackagePath;
                    }
                    //子包名
                    String subPackageName = fileName;
                    if (StringUtils.isNotEmpty(packageName)) {
                        subPackageName = packageName + "." + subPackageName;
                    }
                    //递归调用
                    addClass(classList, subPackagePath, subPackageName);
                }
            }
        } catch (Exception e) {
            LogUtil.error(e, logger, "添加类出错");
        }
    }

    private void doAddClass(List<Class<?>> classList, String className) {
        //加载类
        Class<?> cls = ClassUtil.loadClass(className, false);
        //判断是否可以添加类
        if (checkAddClass(cls)) {
            classList.add(cls);
        }
    }

    public abstract boolean checkAddClass(Class<?> cls);

}
