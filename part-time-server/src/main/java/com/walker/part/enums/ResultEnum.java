package com.walker.part.enums;

/**
 * @description: ResultEnum
 * @author: Walker
 * @date: 2024-04-21 11:23:23
 * @version: 1.0.0
 */
public enum ResultEnum {
    /**
     * 成功
     */
    SUCCESS(200, "成功!"),

    /**
     * 失败
     */
    FAILED(300, "失败!");

    private Integer code;
    private String message;

    ResultEnum() {
    }

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
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
}
