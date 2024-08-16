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
 * @Description 2. Work Queues
 * @Author WuYiLong
 * @Date 2024/8/14 16:12
 */
@Slf4j
@Service
public class SecondModeExample {

    public static final String QUEUE = "secondModeQueue";
    public static final String EXCHANGE = "secondModeExchange";
    public static final String ROUTE_KEY = "secondModeRouteKey";

    @RabbitListener(bindings = @QueueBinding(value =@Queue(name = QUEUE),exchange = @Exchange(name = EXCHANGE),key = ROUTE_KEY))
    public void consumer1(String data, Channel channel, Message message) throws IOException {
            log.info("消费者1正在消费:{}",data);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(bindings = @QueueBinding(value =@Queue(name = QUEUE),exchange = @Exchange(name = EXCHANGE),key = ROUTE_KEY))
    public void consumer2(String data, Channel channel, Message message) throws IOException {
        log.info("消费者2正在消费:{}",data);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}
