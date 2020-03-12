package com.wyl.springbootshiro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @ClassName UserDto
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-11 14:40
 * @Version 1.0.0
 **/
@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String passwordSalt;

    private Integer roleId;

    private boolean status;



}
