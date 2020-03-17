package com.wyl.springbootshiro.controller;

import com.wyl.springbootshiro.common.Response;
import com.wyl.springbootshiro.dao.UserDao;
import com.wyl.springbootshiro.model.User;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName UserController
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-12 15:34
 * @Version 1.0.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userDao")
    private UserDao userDao;


    @RequiresPermissions("/user/page")
    @GetMapping("/page")
    public Response<User> getUserPage(@RequestParam(value = "pageNum",defaultValue = "1")  Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1, pageSize, sort);
        Page<User> userPage = userDao.findAll(pageable);
        return new Response(200,"操作成功",userPage);
    }

}
