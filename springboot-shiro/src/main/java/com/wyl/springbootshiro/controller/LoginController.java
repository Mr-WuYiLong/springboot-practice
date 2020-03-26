package com.wyl.springbootshiro.controller;

import com.wyl.springbootshiro.common.Response;
import com.wyl.springbootshiro.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName LoginController
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-11 16:19
 * @Version 1.0.0
 **/
@RestController
@Slf4j
public class LoginController {

//    @GetMapping("/unAuthorized")
//    public Response unAuthorized() {
//        return new Response(403,"你没有权限",null);
//    }

    @GetMapping("/notLogin")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response notLogin() {
        return new Response(HttpStatus.UNAUTHORIZED.value(),"你没有访问凭证",null);
    }

    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        if(!subject.isAuthenticated()) {
            throw new AuthenticationException("认证失败");
        }
        return new Response(200,"登录成功",user);
    }

    @GetMapping("/logout")
    public Response logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Response(200,"退出成功",null);
    }
}
