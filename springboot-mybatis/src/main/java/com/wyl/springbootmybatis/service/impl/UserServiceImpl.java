package com.wyl.springbootmybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyl.springbootmybatis.dao.UserMapper;
import com.wyl.springbootmybatis.model.User;
import com.wyl.springbootmybatis.service.UserService;
import com.wyl.springbootmybatis.utils.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author YiLong Wu
 * @Date 2020/8/25 下午5:21
 * @Version 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        // 放在查询的前面才有效
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userMapper.selectPage();
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        PageResult pageResult = new PageResult();

        pageResult.setPageNum(userPageInfo.getPageNum());
        pageResult.setPageSize(userPageInfo.getPageSize());
        pageResult.setTotal(userPageInfo.getSize());
        pageResult.setContent(userPageInfo.getList());
        return pageResult;
    }
}
