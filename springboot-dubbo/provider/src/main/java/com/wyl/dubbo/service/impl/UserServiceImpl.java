package com.wyl.dubbo.service.impl;

import com.wyl.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;

import com.wyl.User;
/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-26 10:23
 * @Version 1.0.0
 **/
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUserList() {
        User user = new User("张三", "123456");
        ArrayList<User> list = new ArrayList<>();
        list.add(user);
        return list;
    }
}
