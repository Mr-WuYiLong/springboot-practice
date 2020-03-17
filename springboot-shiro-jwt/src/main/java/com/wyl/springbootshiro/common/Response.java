package com.wyl.springbootshiro.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName Response
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-11 16:20
 * @Version 1.0.0
 **/
@Data
@AllArgsConstructor
public class Response<T> {

    private Integer code;

    private String message;

    private T data;
}
