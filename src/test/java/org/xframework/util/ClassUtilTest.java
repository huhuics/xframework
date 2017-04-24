/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.util;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.xframework.BaseTest;

/**
 * 测试{@link ClassUtil}
 * @author HuHui
 * @version $Id: ClassUtilTest.java, v 0.1 2017年4月18日 下午8:05:53 HuHui Exp $
 */
public class ClassUtilTest extends BaseTest {

    @Test
    public void testGetResource() throws Exception {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        String packageName = "org.xframe.test";
        String path = packageName.replace(".", "/");
        ClassLoader classLoader = ClassUtil.getClassLoader();
        Enumeration<URL> urls = classLoader.getResources(path);

        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            String protocal = url.getProtocol();
            if (protocal.equals("file")) {
                String packagePath = url.getPath().replace("%20", " ");
                LogUtil.info(logger, "packagePath={0}", packagePath);
                addClass(classList, packagePath, packageName);
            }
        }
    }

    private void addClass(List<Class<?>> classList, String packagePath, String packageName) {
        try {
            File[] files = new File(packagePath).listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return (file.isFile() && file.getName().endsWith(".class")) || (file.isDirectory());
                }
            });
            for (File file : files) {
                String fileName = file.getName();
                if (file.isFile()) {
                    String className = fileName.substring(0, fileName.lastIndexOf("."));
                    className = packageName + "." + className;
                    doAddClass(classList, className);
                } else {
                    String subPackagePath = fileName;
                    if (StringUtils.isNotEmpty(packagePath)) {
                        subPackagePath = packagePath + "/" + subPackagePath;
                    }
                    String subPackageName = fileName;
                    if (StringUtils.isNotEmpty(packageName)) {
                        subPackageName = packageName + "." + subPackageName;
                    }
                    addClass(classList, subPackagePath, subPackageName);
                }
            }
        } catch (Exception e) {
            LogUtil.error(e, logger, "添加类出错");
        }
    }

    private void doAddClass(List<Class<?>> classList, String className) {
        Class<?> cls = ClassUtil.loadClass(className, false);
        classList.add(cls);
    }

}
