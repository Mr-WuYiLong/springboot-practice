package com.wyl.springbootmybatis.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyl.springbootmybatis.dao.UserMapper;
import com.wyl.springbootmybatis.model.User;
import com.wyl.springbootmybatis.service.UserService;
import com.wyl.springbootmybatis.utils.PageResult;
import com.wyl.springbootmybatis.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description user控制器
 * @Author YiLong Wu
 * @Date 2020/8/25 下午3:57
 * @Version 1.0.0
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/findPage")
    public Response findPage(@RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "2",required = false) int pageSize){

        PageResult pageResult = userService.findPage(pageNum, pageSize);
        return new Response(200,"操作成功",pageResult);
    }
}
