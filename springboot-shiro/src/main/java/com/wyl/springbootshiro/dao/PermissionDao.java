package com.wyl.springbootshiro.dao;

import com.wyl.springbootshiro.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName Permission
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-12 15:10
 * @Version 1.0.0
 **/
@Repository
public interface PermissionDao extends JpaRepository<Permission, Integer> {


}
