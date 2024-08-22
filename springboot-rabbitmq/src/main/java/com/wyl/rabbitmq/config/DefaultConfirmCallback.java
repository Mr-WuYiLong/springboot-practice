package com.wyl.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @Description 确认模式-消息发送到交换器后触发回调
 * @Author WuYiLong
 * @Date 2024/8/8 9:27
 */
@Slf4j
public class DefaultConfirmCallback implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("*****是否成功处理消息->{}",ack);
        log.info("correlationData->{}",correlationData != null?correlationData.getId():null);
        log.info("造成的情况->{}",cause);
    }
}
