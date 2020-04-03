package com.wyl.springbootshiro.dao;

import com.wyl.springbootshiro.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName RoleDao
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-12 15:18
 * @Version 1.0.0
 **/
@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
}
