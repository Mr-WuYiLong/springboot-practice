package com.wyl.redis.component;

import com.wyl.redis.constant.RedisConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description redis的订阅
 * @Author WuYiLong
 * @Date 2024/7/30 9:35
 */
@Slf4j
@Component
public class RedisSubscriber {

    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer;

    @PostConstruct
    public void init() {
        redisMessageListenerContainer.addMessageListener(((message, pattern) -> {
            log.info("***********"+message.toString());
        }),new ChannelTopic(RedisConst.DEFAULT_TOPIC));

    }

}
