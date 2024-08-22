package com.wyl.rabbitmq.client.config;

import com.wyl.common.config.RabbitRpcConfig;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/19 17:20
 */
@Configuration
public class RabbitClientConfig {

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(RabbitRpcConfig.REPLY_QUEUE); // 监听的队列
        simpleMessageListenerContainer.setMessageListener(rabbitTemplate(connectionFactory));
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());  // 消息转换器，支持转换对象
        rabbitTemplate.setReplyAddress(RabbitRpcConfig.REPLY_QUEUE); // 使用 RabbitTemplate发送和接收消息,并设置回调队列地址
        rabbitTemplate.setReplyTimeout(60000);
        return rabbitTemplate;
    }
}
