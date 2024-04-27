package com.walker.part.utils;

import com.walker.part.enums.ResultEnum;

/**
 * @description: 公共返回类型
 * @author: Walker
 * @date: 2024-04-21 11:13:13
 * @version: 1.0.0
 */
public class Result<T> {
    private Integer code;

    private String message;

    private T data;

    public static <T> Result<T> success() {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), message, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> Result<T> success(ResultEnum successResult) {
        return new Result<>(successResult.getCode(), successResult.getMessage(), null);
    }

    public static <T> Result<T> success(ResultEnum successResult, T data) {
        return new Result<>(successResult.getCode(), successResult.getMessage(), data);
    }


    public static <T> Result<T> failed() {
        return new Result<>(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage(), null);
    }

    public static <T> Result<T> failed(String message) {
        return new Result<>(ResultEnum.FAILED.getCode(), message, null);
    }

    public static <T> Result<T> failed(T data) {
        return new Result<>(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage(), data);
    }

    public static <T> Result<T> failed(String message, T data) {
        return new Result<>(ResultEnum.FAILED.getCode(), message, data);
    }

    public static <T> Result<T> failed(ResultEnum errorResult) {
        return new Result<>(errorResult.getCode(), errorResult.getMessage(), null);
    }

    public static <T> Result<T> failed(ResultEnum errorResult, T data) {
        return new Result<>(errorResult.getCode(), errorResult.getMessage(), data);
    }

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static <T> Result<T> instance(Integer code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
