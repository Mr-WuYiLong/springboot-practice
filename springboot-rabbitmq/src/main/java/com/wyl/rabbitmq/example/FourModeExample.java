package com.wyl.rabbitmq.example;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Description 4. Routing
 * @Author WuYiLong
 * @Date 2024/8/15 14:05
 */
@Slf4j
@Service
public class FourModeExample {

    public static final String QUEUE1 = "fourModeQueue1";
    public static final String QUEUE2 = "fourModeQueue2";
    public static final String EXCHANGE = "fourModeExchange";
    public static final String ROUTING_KEY1 = "fourModeRoutingKey1";
    public static final String ROUTING_KEY2 = "fourModeRoutingKey2";
    public static final String ROUTING_KEY3 = "fourModeRoutingKey3";

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = QUEUE1),exchange = @Exchange(value = EXCHANGE),key = {ROUTING_KEY1,ROUTING_KEY2,ROUTING_KEY3}))
    public void consumer1(String data, Channel channel, Message message) throws Exception {
        log.info("consumer1->{}", data);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = QUEUE2),exchange = @Exchange(value = EXCHANGE),key = {ROUTING_KEY1}))
    public void consumer2(String data, Channel channel, Message message) throws Exception {
        log.info("consumer2->{}", data);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
