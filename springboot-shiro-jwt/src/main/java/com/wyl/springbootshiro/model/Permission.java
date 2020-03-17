package com.wyl.springbootshiro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @ClassName Permission
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-11 14:53
 * @Version 1.0.0
 **/
@Data
@Entity
@Table(name = "permission")
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String permissionName;

    private String action;

    private boolean status;

}
