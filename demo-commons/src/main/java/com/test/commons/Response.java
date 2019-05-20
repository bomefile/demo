package com.test.commons;

import java.io.Serializable;
import java.util.Date;

/**
 * Description
 * <p>
 * DATE 2019/5/20.
 *
 * @author wangliangliang.
 */
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 5533028371111297159L;

    /**
     * 执行成功
     */
    public static final int SUCCESS = 200;

    /**
     * 请求参数格式不正确
     */
    public static final int ILLEGAL_REQUEST_PARAMETER = 400;

    /**
     * 需要登录
     */
    public static final int LOGIN_REQUIRED = 401;

    /**
     * 需要注册
     */
    public static final int REGISTER_REQUIRED = 402;

    /**
     * 没有权限
     */
    public static final int UNAUTHENCATED = 403;

    /**
     * 通用服务端异常
     */
    public static final int CAUGHT_EXCEPTION = 500;

    /**
     * 系统可预见的业务规则的错误
     */
    public static final int FAILURE = 501;

    // 接口特殊的响应 响应码从901开始
    /**
     * 服务器响应消息
     */
    private String message;

    /**
     * 服务器响应数据
     */
    private T data;

    /**
     * 服务器响应码
     */
    private int code;

    /**
     * 服务器响应时间
     */
    private Date serverTime;

    public Response() {
        this.serverTime = new Date();
    }

    public Response(String message, T data, int code) {
        this();
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public Response(T data, int code) {
        this();
        this.data = data;
        this.code = code;
    }

    /**
     * 响应
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> response(int code, String message) {
        return response(code, message, null);
    }

    /**
     * 响应
     * @param code
     * @param message
     * @param result
     * @param <T>
     * @return
     */
    public static <T> Response<T> response(int code, String message, T result) {
        return new Response<T>(message, result, code);
    }

    /**
     * 响应
     *
     * @param response
     * @param <T>
     * @return
     */
    public static <T> Response<T> response(Response<?> response) {
        return response(response.getCode(), response.getMessage());
    }


    /**
     * 成功
     * @param <T>
     * @return
     */
    public static <T> Response<T> success() {
        return success("", null);
    }

    /**
     * 成功
     * @param result
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(T result) {
        return success("", result);
    }

    /**
     * 成功
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(String message) {
        return success(message, null);
    }

    /**
     * 成功
     * @param message
     * @param result
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(String message, T result) {
        return response(SUCCESS, message, result);
    }


    /**
     * 非法参数
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> illegalRequestParameter(String message) {
        return response(ILLEGAL_REQUEST_PARAMETER, message, null);
    }

    /**
     * 登录失败
     * @param <T>
     * @return
     */
    public static <T> Response<T> loginRequired() {
        return response(LOGIN_REQUIRED, "请登录", null);
    }

    /**
     * 未注册
     * @param <T>
     * @return
     */
    public static <T> Response<T> registerRequired() {
        return response(REGISTER_REQUIRED, "请注册", null);
    }

    /**
     * 没权限
     * @param <T>
     * @return
     */
    public static <T> Response<T> unauthencated() {
        return response(UNAUTHENCATED, "对不起，您没有访问权限", null);
    }

    /**
     * 服务端异常
     * @param <T>
     * @return
     */
    public static <T> Response<T> caughtException() {
        return caughtException("抱歉, 请稍后再试");
    }

    /**
     * 服务端异常
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> caughtException(String message) {
        return response(CAUGHT_EXCEPTION, message, null);
    }

    /**
     * 执行失败
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> failure(String message) {
        return response(FAILURE, message, null);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 是否成功
     * @return
     */
    public boolean isSuccess() {
        return this.code == SUCCESS;
    }

    public Date getServerTime() {
        return serverTime;
    }
}
