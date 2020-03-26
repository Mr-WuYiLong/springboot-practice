//package com.wyl.springbootredis.config;
//
//import com.wyl.springbootredis.receiver.UserReceiver;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.listener.PatternTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
//
///**
// * @ClassName ReceiverConfig
// * @Description
// * @Author yilongwu
// * @DATE 2020-03-20 17:31
// * @Version 1.0.0
// **/
//@Configuration
//public class ReceiverConfig {
//
//    @Bean
//    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory,MessageListenerAdapter messageAdapter) {
//        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
//        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
//        redisMessageListenerContainer.addMessageListener(messageAdapter,new PatternTopic("user"));
//        return redisMessageListenerContainer;
//    }
//
//    @Bean
//    public MessageListenerAdapter messageAdapter(UserReceiver userReceiver) {
//
//        return new MessageListenerAdapter(userReceiver,"onMessage");
//    }
//
//    @Bean
//    public UserReceiver userReceiver() {
//        return new UserReceiver();
//    }
//}
