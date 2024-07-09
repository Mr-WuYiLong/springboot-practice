package com.wyl.redis;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/2 9:56
 */
@EnableCaching
@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
       SpringApplication.run(RedisApplication.class);
    }
}
