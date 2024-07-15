package com.wyl.redis.service;

import cn.hutool.core.lang.tree.Tree;

import java.util.List;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/3 17:32
 */
public interface DictionaryOperate {

    /**
     * 列表
     * @return
     */
    List list(String key);

    /**
     * 树
     * @param key
     * @return
     */
    List<Tree<String>> tree(String key);

    /**
     * 支持类型
     * @return
     */
    String supportType();

}
