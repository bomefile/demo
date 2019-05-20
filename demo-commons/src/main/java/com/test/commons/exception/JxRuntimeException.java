package com.test.commons.exception;

/**
 * 定义全局业务运行时异常
 * 
 * @author wanghui59@jd.com
 * @since 2017-12-29
 */
public class JxRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -7445250527833979216L;

    public JxRuntimeException() {
        super();
    }

    public JxRuntimeException(String message) {
        super(message);
    }

    public JxRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public JxRuntimeException(Throwable cause) {
        super(cause);
    }
    
}
