package com.wyl.springbootshiro.dao;

import com.wyl.springbootshiro.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName RolePermissionDao
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-12 15:07
 * @Version 1.0.0
 **/
@Repository
public interface RolePermissionDao extends JpaRepository<RolePermission,Integer> {

    /**
     * 根据角色名称查找对应的权限
     * @param roleId
     * @return
     */
    List<RolePermission> findByRoleId(@Param("roleId") Integer roleId);
}
