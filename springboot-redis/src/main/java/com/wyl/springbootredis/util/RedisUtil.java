package com.wyl.springbootredis.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ClassName RedisUtil
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-19 16:42
 * @Version 1.0.0
 **/
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;


    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        return o.toString();
    }

}


