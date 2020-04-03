package com.wyl.springbootshiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @Description InvalidTokenException 不合法的token异常
 * @Author YiLong Wu
 * @Date 2020-03-13 22:44
 * @Version 1.0.0
 */
public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException() {

    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
