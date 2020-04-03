package com.wyl.springbootshiro.exception;

/**
 * @ClassName UnknowUsenameAndPasswordException
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-17 15:35
 * @Version 1.0.0
 **/
public class UnknowUsenameAndPasswordException extends RuntimeException {

    public UnknowUsenameAndPasswordException() {
    }

    public UnknowUsenameAndPasswordException(String message) {
        super(message);
    }
}
