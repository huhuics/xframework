/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc.fault;

/**
 * 认证异常(当非法访问时抛出)
 * @author HuHui
 * @version $Id: AuthcException.java, v 0.1 2017年4月22日 上午11:26:45 HuHui Exp $
 */
public class AuthcException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = 6937923180238454316L;

    public AuthcException() {
        super();
    }

    public AuthcException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthcException(String message) {
        super(message);
    }

    public AuthcException(Throwable cause) {
        super(cause);
    }

}
