package com.wyl.springbootredis.controller;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName TestController
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-24 14:31
 * @Version 1.0.0
 **/
@RestController
@Slf4j
public class TestController {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @GetMapping("/get")
    public void get() {
//        redisTemplate.sla


        long seconds = DateUtil.currentSeconds();
        for (int i = 0; i < 100; i++) {
            redisTemplate.opsForValue().get("name" + i);
        }

//        List<RedisClientInfo> clientList = redisTemplate.getClientList();
//        Object name = redisTemplate.opsForValue().get("name");

        log.info("**********{}******", DateUtil.currentSeconds() - seconds);
    }
}
