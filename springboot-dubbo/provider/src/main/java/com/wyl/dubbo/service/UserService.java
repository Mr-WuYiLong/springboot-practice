package com.wyl.dubbo.service;

import com.wyl.User;

import java.util.List;

/**
 * @InterfaceName UserService
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-26 10:11
 * @Version 1.0.0
 **/
public interface UserService {

    List<User> getUserList();

}
