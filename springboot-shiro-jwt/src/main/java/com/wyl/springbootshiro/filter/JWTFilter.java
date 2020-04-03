package com.wyl.springbootshiro.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wyl.springbootshiro.common.ResponseError;
import com.wyl.springbootshiro.exception.InvalidTokenException;
import com.wyl.springbootshiro.shiro.JWTToken;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName JWTFilter
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-12 17:57
 * @Version 1.0.0
 **/
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {

    // 是否允许访问
    @SneakyThrows
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("*********进入isAccessAllowed********");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        ResponseError responseError = new ResponseError();
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                log.info("****InvalidTokenException****");
                if (e instanceof InvalidTokenException) {
                    request.getRequestDispatcher("/invalidToken").forward(request, response);
                } else if (e instanceof UnknownAccountException) {
                    request.getRequestDispatcher("/unknownAccount").forward(request, response);
                }
                return true;
            }
        }

        // 当没有带token访问时
        responseError.setCode(401);
        responseError.setMessage("没有访问凭证");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(responseError));
        return false;
    }

    // 执行登录
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        log.info("************进入executeLogin*******");
        String token = ((HttpServletRequest) request).getHeader("Authorization");


        JWTToken jwtToken = new JWTToken(token);
        getSubject(request, response).login(jwtToken);
        return true;
    }

    // 是否接受登录
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        log.info("*******************进入isLoginAttempt********");
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        return StringUtils.isNotBlank(token) && token.startsWith("Bearer ");
    }
}

