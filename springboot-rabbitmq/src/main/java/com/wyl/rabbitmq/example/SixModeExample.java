package com.wyl.rabbitmq.example;

import com.rabbitmq.client.Channel;
import com.wyl.common.config.RabbitRpcConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/16 14:31
 */
@Slf4j
@Service
public class SixModeExample {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitRpcConfig.RPC_QUEUE)
    public void consumer(Channel channel,Message message) throws IOException {
        log.info("consumer->{}", StringUtils.toEncodedString(message.getBody(), StandardCharsets.UTF_8));
        Message build = MessageBuilder.withBody("收到消息了".getBytes(StandardCharsets.UTF_8)).build();
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        String correlationId = message.getMessageProperties().getCorrelationId();
        CorrelationData correlationData = new CorrelationData(correlationId);
        rabbitTemplate.sendAndReceive(RabbitRpcConfig.EXCHANGE, RabbitRpcConfig.REPLY_ROUTING_KEY,build ,correlationData);

    }
}
