/**
 *
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package org.xframework.mvc.fault;

/**
 * 授权异常(当授权无效时抛出)
 * @author HuHui
 * @version $Id: AuthzException.java, v 0.1 2017年4月22日 上午11:31:32 HuHui Exp $
 */
public class AuthzException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = -3623512556628204696L;

    public AuthzException() {
        super();
    }

    public AuthzException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthzException(String message) {
        super(message);
    }

    public AuthzException(Throwable cause) {
        super(cause);
    }

}
