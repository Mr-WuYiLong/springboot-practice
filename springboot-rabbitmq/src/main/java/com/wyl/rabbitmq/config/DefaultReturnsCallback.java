package com.wyl.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @Description 退回模式-交换机将消息发送到对应队列失败时触发
 * @Author WuYiLong
 * @Date 2024/8/8 9:35
 */
@Slf4j
public class DefaultReturnsCallback implements RabbitTemplate.ReturnsCallback {

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("错误码->{}",returned.getReplyCode());
        log.info("错误信息->{}",returned.getReplyText());
        log.info("交换机->{}",returned.getExchange());
        log.info("路由规则->{}",returned.getExchange());
        log.info("发送的消息->{}",new String(returned.getMessage().getBody()));
    }
}
