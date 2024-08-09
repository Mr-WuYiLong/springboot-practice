package com.wyl.common.exception;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/12 10:09
 */
public class BusinessException extends RuntimeException{

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }
}
