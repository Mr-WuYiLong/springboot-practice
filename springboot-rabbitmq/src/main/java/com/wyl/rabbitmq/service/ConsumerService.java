package com.wyl.rabbitmq.service;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/2 14:22
 */
@Service
public class ConsumerService {

    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

    @RabbitListener(queues = {"fanoutQueue1"})
    public void simpleMessage1(String msg, Channel channel, Message message) throws IOException {
        log.info("simpleMessage1-发送的消息->{}",msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(queues = {"fanoutQueue2"})
    public void simpleMessage2(String msg, Channel channel, Message message) throws IOException {
        log.info("simpleMessage2-发送的消息->{}",msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(queues = {"fanoutQueue3"})
    public void simpleMessage3(String msg, Channel channel, Message message) throws IOException {
        log.info("simpleMessage3-发送的消息->{}",msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(queues = {"simple-queue"})
    public void simpleMessage4(String msg, Channel channel, Message message) throws IOException {
        log.info("simpleMessage4-发送的消息->{}",msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


}
