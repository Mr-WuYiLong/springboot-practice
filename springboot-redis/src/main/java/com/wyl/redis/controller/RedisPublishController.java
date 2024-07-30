package com.wyl.redis.controller;

import com.wyl.common.bean.ResponseData;
import com.wyl.redis.constant.RedisConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/30 9:45
 */
@Api(tags = "redis发布控制器")
@RestController
@RequestMapping(value = "redisPublish")
public class RedisPublishController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer;

    @ApiOperation(value = "发布")
    @GetMapping(value = "publish")
    public ResponseData publish(String message) {
        redisTemplate.convertAndSend(RedisConst.DEFAULT_TOPIC,message);
        return ResponseData.success();
    }

    @ApiOperation(value = "订阅者添加主题")
    @GetMapping(value = "subscriberChannelTopic")
    public ResponseData addSubscriberChannelTopic(String channelTopic) {
        redisMessageListenerContainer.addMessageListener(((message, pattern) -> {
            System.out.println("正在监听消息--"+message);
        }),new ChannelTopic(channelTopic));
        return ResponseData.success();
    }

    @ApiOperation(value = "发布者添加主题和消息")
    @GetMapping(value = "addPublishChannelTopicAndMessage")
    public ResponseData addPublishChannelTopicAndMessage(String channelTopic,String message) {
        redisTemplate.convertAndSend(channelTopic,message);
        return ResponseData.success();
    }
}
