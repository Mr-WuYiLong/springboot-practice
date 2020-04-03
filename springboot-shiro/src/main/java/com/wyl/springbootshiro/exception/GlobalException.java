package com.wyl.springbootshiro.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description GlobaException 全局的异常配置
 * @Author YiLong Wu
 * @Date 2020-03-11 22:28
 * @Version 1.0.0
 */
@RestControllerAdvice
public class GlobalException {

    /**
     * 处理用户名密码错误的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus
    public ResponseError unknownAccountExceptionAndIncorrectCredentialsException(AuthenticationException e) {
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "用户名或密码错误");
    }

    /**
     * 处理权限不足的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseError authorizationException(AuthorizationException e) {
        return new ResponseError(HttpStatus.FORBIDDEN.value(), "你没有权限访问");
    }


}
