/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.core.impl.support;

import java.lang.annotation.Annotation;

/**
 * 
 * @author HuHui
 * @version $Id: AnnotationClassTemplate.java, v 0.1 2017年4月8日 上午8:52:45 HuHui Exp $
 */
public abstract class AnnotationClassTemplate extends ClassTemplate {

    protected final Class<? extends Annotation> annotationClass;

    protected AnnotationClassTemplate(String packageName, Class<? extends Annotation> annotationClass) {
        super(packageName);
        this.annotationClass = annotationClass;
    }

}
