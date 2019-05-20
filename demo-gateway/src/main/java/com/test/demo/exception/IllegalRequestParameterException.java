package com.test.demo.exception;

/**
 * 非法的请求参数. 当请求参数格式错误时抛出
 * 
 * @author wanghui59@jd.com
 * @since 2017-12-19
 */
public class IllegalRequestParameterException extends GatewayException {

    private static final long serialVersionUID = 4376339362966143345L;
    
    public IllegalRequestParameterException() {
        super();
    }

    public IllegalRequestParameterException(String message) {
        super(message);
    }

    public IllegalRequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRequestParameterException(Throwable cause) {
        super(cause);
    }

}
