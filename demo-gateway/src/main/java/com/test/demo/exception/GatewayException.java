package com.test.demo.exception;


import com.test.commons.exception.JxRuntimeException;

/**
 * 网关层统一异常
 * 
 * @author wanghui59@jd.com
 * @since 2017-12-29
 */
public class GatewayException extends JxRuntimeException {

    private static final long serialVersionUID = 411118218833966495L;
    
    public GatewayException() {
        super();
    }

    public GatewayException(String message) {
        super(message);
    }

    public GatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatewayException(Throwable cause) {
        super(cause);
    }

}
