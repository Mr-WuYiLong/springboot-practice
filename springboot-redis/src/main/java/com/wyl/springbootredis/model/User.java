package com.wyl.springbootredis.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-20 18:17
 * @Version 1.0.0
 **/
@Data
@AllArgsConstructor
public class User implements Serializable {
    private String username;
    private String password;
}
