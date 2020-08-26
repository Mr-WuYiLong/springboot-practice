package com.wyl.springbootmybatis.service;

import com.wyl.springbootmybatis.utils.PageResult;

/**
 * @Description
 * @Author YiLong Wu
 * @Date 2020/8/25 下午4:40
 * @Version 1.0.0
 */
public interface UserService {

    PageResult findPage(int pageNum,int pageSize);
}
