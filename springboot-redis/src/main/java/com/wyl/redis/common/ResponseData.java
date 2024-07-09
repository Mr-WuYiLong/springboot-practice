package com.wyl.redis.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

/**
 * @Description 公共的响应消息体
 * @Author WuYiLong
 * @Date 2024/7/3 17:24
 */
@Data
@ApiModel(value = "responseData")
public class ResponseData<T> {
    @ApiModelProperty(value = "标识码")
    private int code;
    @ApiModelProperty(value = "消息")
    private String msg;
    @ApiModelProperty(value = "数据")
    private T data;

    public ResponseData(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResponseData(int code,String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseData<T> create(int code,String msg,T data) {
        return new ResponseData<T>(code,msg,data);
    }

    public static <T> ResponseData<T> success() {
        return new ResponseData<T>(1,"操作成功");
    }

    public static <T> ResponseData<T> successInstance(T data) {
        return new ResponseData<T>(1,"操作成功",data);
    }

    public static <T> ResponseData<T> fail() {
        return new ResponseData<T>(-1,"操作失败");
    }
}
