package com.wyl.springbootshiro.controller;

import com.wyl.springbootshiro.Util.JwtUtil;
import com.wyl.springbootshiro.common.Response;
import com.wyl.springbootshiro.dao.UserDao;
import com.wyl.springbootshiro.exception.InvalidTokenException;
import com.wyl.springbootshiro.exception.UnknowUsenameAndPasswordException;
import com.wyl.springbootshiro.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @Resource(name = "userDao")
    private UserDao userDao;

    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        User byUser = userDao.findByUsername(user.getUsername());
        if (byUser == null || !byUser.getPassword().equals(user.getPassword())) {
            throw new UnknowUsenameAndPasswordException();
        }
        // 生成token
        String token = JwtUtil.generateToken(user.getUsername());
        return new Response(200, "登录成功", token);
    }

    @GetMapping("/logout")
    public Response logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Response(200, "退出成功", null);
    }


}
