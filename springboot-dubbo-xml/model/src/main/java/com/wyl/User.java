package com.wyl;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-26 16:57
 * @Version 1.0.0
 **/
@Data
@AllArgsConstructor
public class User implements Serializable {

    private String username;
    private String password;
}
