package com.wyl.springbootshiro.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description ResponseError 公共的异常响应结果
 * @Author YiLong Wu
 * @Date 2020-03-11 22:26
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {

    private Integer code;

    private String message;
}
