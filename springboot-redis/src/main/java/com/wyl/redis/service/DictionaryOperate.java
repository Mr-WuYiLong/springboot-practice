package com.wyl.redis.service;

import java.util.List;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/3 17:32
 */
public interface DictionaryOperate<T> {

    /**
     * 列表
     * @return
     */
    List<T> list();
}
