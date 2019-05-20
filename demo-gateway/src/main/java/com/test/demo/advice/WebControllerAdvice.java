package com.test.demo.advice;

import com.test.commons.Response;
import com.test.commons.exception.JxRuntimeException;
import com.test.demo.exception.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * Controller层的异常处理
 * 
 * @author wanghui59@jd.com
 * @since 2017-12-26
 */
@ControllerAdvice
public class WebControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebControllerAdvice.class);
    
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Response<String>> handleException(Throwable ex) {
        LOGGER.error("WebControllerAdvice handleException:{}", ex.getClass().getName(), ex); // 打印异常
        if (ex instanceof GatewayException) {

            if (ex instanceof IllegalRequestParameterException) { // 请求参数校验失败
                return renderResponse(Response.illegalRequestParameter(ex.getMessage()));
            }

            if (ex instanceof LoginRequiredException) { // 需要登录
                return renderResponse(Response.loginRequired());
            }

            if (ex instanceof RegisterRequiredException) { // 需要注册
                return renderResponse(Response.registerRequired());
            }

            if (ex instanceof AuthencationException) { // 没有权限
                return renderResponse(Response.unauthencated());
            }
        }

        // --------------------------- 针对某些异常的特殊处理 --------------------------
        if (ex instanceof MissingServletRequestParameterException) {
            LOGGER.error(ex.toString()); // 打印异常
            MissingServletRequestParameterException e = (MissingServletRequestParameterException) ex;
            return renderResponse(Response.illegalRequestParameter((e.getParameterName() + "不能为空")));
        }
        
        if (ex instanceof MethodArgumentTypeMismatchException) {
            LOGGER.error(ex.toString()); // 打印异常
            MethodArgumentTypeMismatchException e = (MethodArgumentTypeMismatchException) ex;
            return renderResponse(Response.illegalRequestParameter(e.getPropertyName() + "必须为" + e.getRequiredType() + "类型"));
        }

        if(ex instanceof BindException){
            String msg = processBindException((BindException) ex);
            return renderResponse(Response.caughtException(msg));
        }
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            LOGGER.error(ex.toString()); // 打印异常
            HttpRequestMethodNotSupportedException e = (HttpRequestMethodNotSupportedException) ex;
            return renderResponse(Response.illegalRequestParameter("不支持" + e.getMethod() + "方式的请求"));
        }
        // --------------------------- 针对某些异常的特殊处理 --------------------------
        
        LOGGER.error(ExceptionUtils.getStackTrace(ex)); // 打印异常堆栈
        
        if (ex instanceof JxRuntimeException) { // 其它异常
            return renderResponse(Response.caughtException(ex.getMessage()));
        }
        
        return renderResponse(Response.caughtException()); // 默认输出
    }

    /**
     * 处理参数绑定错误
     *
     * @author jiangwang
     * 10:46 2018/5/14
     */
    private String processBindException(BindException bindException){
        List<ObjectError> errors = bindException.getAllErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for (ObjectError error : errors) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(error.getDefaultMessage());
        }
        return stringBuilder.toString();
    }


    public ResponseEntity<Response<String>> renderResponse(Response<String> response) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
    
}
