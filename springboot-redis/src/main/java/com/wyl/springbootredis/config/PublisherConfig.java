//package com.wyl.springbootredis.config;
//
//import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// * @ClassName PublisherConfig
// * @Description
// * @Author yilongwu
// * @DATE 2020-03-20 17:07
// * @Version 1.0.0
// **/
//@Configuration
//public class PublisherConfig {
//
//    @Bean
//    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        template.setDefaultSerializer(new FastJsonRedisSerializer(Object.class));
////        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
////        template.setDefaultSerializer(new JdkSerializationRedisSerializer());
//        return template;
//    }
//}
