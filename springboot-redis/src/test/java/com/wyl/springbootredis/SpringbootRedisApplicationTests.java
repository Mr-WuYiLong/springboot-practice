package com.wyl.springbootredis;


import cn.hutool.core.date.DateUtil;
import com.wyl.springbootredis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest
@Slf4j
class SpringbootRedisApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
//        List<Object> exec = redisTemplate.exec();
        redisUtil.set("password", "123456");

        log.info("*********{}", redisUtil.get("password"));
//        log.info("*********{}",exec);
//        long second1 = DateUtil.currentSeconds();
//
//        System.out.println("开始时间(秒)->"+second1);
//        List list = stringRedisTemplate.executePipelined(new RedisCallback<String>() {
//            @Override
//            public String doInRedis(RedisConnection connection) throws DataAccessException {
//                StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
//                for (Integer i = 0; i < 10000; i++) {
//
//                    stringRedisConnection.get(i.toString());
//
//                }
//
//                return null;
//            }
//        });
//        long second2 = DateUtil.currentSeconds();
//        System.out.println("结束时间(秒)->"+second2);
//        System.out.println("花费时间(秒)->"+(second2-second1));

//        stringRedisTemplate.execute(new RedisCallback<Object>() {
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                StringRedisConnection stringRedisConnection = (StringRedisConnection)connection;
//                for (Integer i = 0; i <500 ; i++) {
//                    stringRedisConnection.get(i.toString());
//                }
//                return null;
//            }
//        });

//
//        List list = redisTemplate.executePipelined(new RedisCallback<String>() {
//
//            @Override
//            public String doInRedis(RedisConnection connection) throws DataAccessException {
//
////               connection.z
//                connection.get(("p1").getBytes());
//                return null;
//            }
//        },redisTemplate.getStringSerializer());
//
//
    }

}
