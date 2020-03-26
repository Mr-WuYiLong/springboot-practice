package com.wyl.springbootredis.test;

import com.wyl.springbootredis.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @ClassName PublishTest
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-20 17:59
 * @Version 1.0.0
 **/
@SpringBootTest
public class PublishTest {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void publish() {
        redisTemplate.convertAndSend("user",new User("张三","123456"));
    }
}
