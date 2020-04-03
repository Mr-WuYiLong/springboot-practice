package com.wyl.provider.service.impl;

import com.wyl.User;
import com.wyl.provider.service.UserService;


import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-26 10:23
 * @Version 1.0.0
 **/
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUserList() {
        User user = new User("张三", "123456");
        ArrayList<User> list = new ArrayList<>();
        list.add(user);
        return list;
    }
}
