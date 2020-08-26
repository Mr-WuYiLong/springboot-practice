package com.wyl.springbootmybatis.utils;

/**
 * @Description 公共响应工具类
 * @Author YiLong Wu
 * @Date 2020/8/25 下午5:35
 * @Version 1.0.0
 */
public class Response {

    private int code;
    private String message;
    private Object data;

    public Response(int code,String message,Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
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
}
