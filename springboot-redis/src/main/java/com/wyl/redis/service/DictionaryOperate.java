package com.wyl.redis.service;

import cn.hutool.core.lang.tree.Tree;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/3 17:32
 */
public interface DictionaryOperate{

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
    <T> List<Tree<T>> tree(String key);

    /**
     * 根据code获取名称
     * @return
     */
    String codeNameMap(String key,String code);

    /**
     * 根据code获取名称
     * @return
     */
    String nameCodeMap(String key,String name);


    /**
     * 根据code查询实体
     * @param key
     * @param code
     * @return
     */
    <T> T getByCode(String key,String code);

    /**
     * 支持类型
     * @return
     */
    String supportType();



}
