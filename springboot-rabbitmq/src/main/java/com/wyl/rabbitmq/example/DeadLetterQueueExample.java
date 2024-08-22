package com.wyl.rabbitmq.example;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Description 死信队列栗子
 * @Author WuYiLong
 * @Date 2024/8/20 9:41
 */
@Slf4j
@Service
public class DeadLetterQueueExample {

    public static final String ORDINARY_QUEUE = "ordinaryQueue";
    public static final String ORDINARY_EXCHANGE = "ordinaryExchange";
    public static final String ORDINARY_ROUTING_KEY = "ordinaryRoutingKey";
    public static final String DEAD_LETTER_QUEUE = "deadLetterQueue";
    public static final String DEAD_LETTER_EXCHANGE = "deadLetterExchange";
    public static final String DEAD_LETTER_ROUTING_KEY = "deadLetterRoutingKey";

    @RabbitListener(queues = DEAD_LETTER_QUEUE)
    public void deadConsumer(Channel channel, Message message) throws IOException {
       log.info("deadConsumer message: {}", new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
