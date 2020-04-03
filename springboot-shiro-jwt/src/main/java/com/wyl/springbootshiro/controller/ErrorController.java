package com.wyl.springbootshiro.controller;


import com.wyl.springbootshiro.common.ResponseError;
import com.wyl.springbootshiro.exception.InvalidTokenException;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description ErrorController 解决异常在过滤器中不能被全局异常处理
 * @Author YiLong Wu
 * @Date 2020-03-16 22:22
 * @Version 1.0.0
 */
@RestController
public class ErrorController {

    @GetMapping("/invalidToken")
    public ResponseError invalidTokenException() {
        throw new InvalidTokenException();
    }

    @GetMapping("/unknownAccount")
    public ResponseError unknownAccountException() {
        throw new UnknownAccountException();
    }

}
