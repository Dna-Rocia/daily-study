package com.daily.exception.utils;

import com.alibaba.fastjson.JSON;
import com.daily.exception.constant.CustomExceptionConst;
import com.daily.exception.handle.CustomException;

import java.io.Serializable;

/**
 * 异常响应体的封装
 *
 * @param <T>
 */
public class Response<T> implements Serializable {

    private int code;

    private String message;

    private Object data;

    public Response() {

    }

    public Response(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public Response(int code, String message, T data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public static <T> Response<T> buildSuccessResponse(T data) {
        return new Response<T>(200, "成功", data);
    }

    public static <T> Response<T> buildFailResponse(String msg) {
        return new Response<T>(CustomExceptionConst.ERROR_0.code, msg, null);
    }

    public static <T> Response<T> buildFailResponse(CustomExceptionConst e) {
        return new Response<T>(e.getCode(), e.getMessage());
    }

    public static <T> Response<T> buildFailResponse(CustomException e) {
        return new Response<T>(e.getCode(), e.getMessage());
    }

    public static <T> Response<T> buildFailResponse(CustomException e, T data) {
        return new Response<T>(e.getCode(), e.getMessage(), data);
    }

    public static <T> Response<T> buildFailResponse(int responseCode, String message, T data) {
        return new Response<T>(responseCode, message, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
