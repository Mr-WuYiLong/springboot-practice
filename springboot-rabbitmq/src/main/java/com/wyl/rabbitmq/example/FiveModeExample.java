package com.wyl.rabbitmq.example;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Description 5. Topics
 * @Author WuYiLong
 * @Date 2024/8/16 10:39
 */
@Slf4j
@Service
public class FiveModeExample {

    public static final String QUEUE1 = "queue1";
    public static final String QUEUE2 = "queue2";
    public static final String EXCHANGE = "exchange";
    public static final String ROUTING_KEY1 = "*.a.*";
    public static final String ROUTING_KEY2 = "*.*.b";
    public static final String ROUTING_KEY3 = "c.#";

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = QUEUE1),exchange = @Exchange(name = EXCHANGE,type = ExchangeTypes.TOPIC),key = ROUTING_KEY1))
    public void consumer1(String data, Channel channel, Message message) throws Exception {
        log.info("consumer1: {}", data);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = QUEUE2),exchange = @Exchange(name = EXCHANGE,type = ExchangeTypes.TOPIC),key = {ROUTING_KEY2,ROUTING_KEY3}))
    public void consumer2(String data, Channel channel, Message message) throws Exception {
        log.info("consumer2: {}", data);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
