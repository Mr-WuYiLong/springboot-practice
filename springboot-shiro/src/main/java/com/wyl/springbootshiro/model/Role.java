package com.wyl.springbootshiro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName Role
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-11 14:51
 * @Version 1.0.0
 **/
@Data
@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roleName;

    private Boolean status;

}
