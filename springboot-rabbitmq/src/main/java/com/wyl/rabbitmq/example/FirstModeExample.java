package com.wyl.rabbitmq.example;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Description 1. "Hello World!"
 * @Author WuYiLong
 * @Date 2024/8/14 15:34
 */
@Slf4j
@Service
public class FirstModeExample {

    public static final String QUEUE = "firstModeQueue";
    public static final String EXCHANGE = "firstModeExchange";
    public static final String ROUTE_KEY = "firstModeRouteKey";

    @RabbitListener(bindings = {@QueueBinding(value = @Queue(name = QUEUE), exchange = @Exchange(name = EXCHANGE), key = {ROUTE_KEY})})
    public void consumer(String data, Channel channel, Message message) throws IOException {
        log.info("第一种模式：生产者-队列-消费者，数据：{}",data);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
