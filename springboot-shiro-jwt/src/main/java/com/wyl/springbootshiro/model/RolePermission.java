package com.wyl.springbootshiro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @ClassName RolePermission
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-12 14:54
 * @Version 1.0.0
 **/
@Data
@Entity
@Table(name = "role_permission")
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer permissionId;

    private Integer roleId;

    private Boolean status;
}

