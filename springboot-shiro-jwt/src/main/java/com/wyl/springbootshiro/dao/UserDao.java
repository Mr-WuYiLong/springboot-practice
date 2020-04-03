package com.wyl.springbootshiro.dao;

import com.wyl.springbootshiro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserDao
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-12 11:47
 * @Version 1.0.0
 **/
@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * 根据用户名查找对应的用户
     *
     * @param username
     * @return
     */
    User findByUsername(@Param("username") String username);
}
