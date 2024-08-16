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
 * @Description 3. Publish/Subscribe
 * @Author WuYiLong
 * @Date 2024/8/14 16:37
 */
@Slf4j
@Service
public class ThirdModeExample {

    public static final String QUEUE1 = "thirdModeQueue1";
    public static final String QUEUE2 = "thirdModeQueue2";
    public static final String EXCHANGE = "thirdModeExchange";

    @RabbitListener(bindings = @QueueBinding(value = @Queue(QUEUE1), exchange = @Exchange(name = EXCHANGE,type = ExchangeTypes.FANOUT)))
    public void consumer1(String data, Channel channel, Message message) throws Exception {
        log.info("consumer1: {}", data);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(QUEUE2), exchange = @Exchange(name=EXCHANGE,type = ExchangeTypes.FANOUT)))
    public void consumer2(String data, Channel channel, Message message) throws Exception {
        log.info("consumer2: {}", data);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
