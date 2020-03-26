package com.wyl.dubbo.Controller;

import com.wyl.User;
import com.wyl.dubbo.service.UserService;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserController
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-26 15:13
 * @Version 1.0.0
 **/
@RestController
public class UserController {

    @Reference(version = "1.0.0")
    private UserService userService;

    @GetMapping("/get")
    public Object getUserList() {
        List<User> userList = userService.getUserList();
        return userList;
    }
}
