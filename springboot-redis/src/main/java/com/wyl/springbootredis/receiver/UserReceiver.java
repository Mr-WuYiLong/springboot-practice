package com.wyl.springbootredis.receiver;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserReceiver
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-20 17:37
 * @Version 1.0.0
 **/
@Slf4j
public class UserReceiver  {

    public void onMessage(Object message) {
        log.info("****来自->{}-频道**发送的消息->{}", message);
    }
}
