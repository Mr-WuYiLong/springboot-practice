package com.wyl.redis.bean;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/10 11:52
 */
@Data
public class TreeBean {
    /**
     * 编码
     */
    private String code;
    /**
     * 父级编码
     */
    private String parentCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 子节点
     */
    private List<TreeBean> children;
}
