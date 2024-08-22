package com.wyl.rabbitmq.config;

import com.wyl.rabbitmq.example.DeadLetterQueueExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/2 14:43
 */
@Slf4j
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setConfirmCallback(new DefaultConfirmCallback());
        rabbitTemplate.setReturnsCallback(new DefaultReturnsCallback());
        rabbitTemplate.setMandatory(true); //设置为true，才会触发DefaultReturnsCallback方法
//        rabbitTemplate.setChannelTransacted(true); // 支持事务,但使用RPC模式时需要关闭
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());  // 消息转换器，支持转换对象
        return rabbitTemplate;
    }

    /**
     * 事务模式和确认模式不能同时开启
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTransactionManager rabbitTransactionManager(ConnectionFactory connectionFactory) {
        return new RabbitTransactionManager(connectionFactory);
    }


    /********************************测试死信队列****************************************/

    @Bean
    public Queue ordinaryQueue() {
        return QueueBuilder.durable(DeadLetterQueueExample.ORDINARY_QUEUE).ttl(5000).deadLetterExchange(DeadLetterQueueExample.DEAD_LETTER_EXCHANGE).deadLetterRoutingKey(DeadLetterQueueExample.DEAD_LETTER_ROUTING_KEY).build();
    }

    @Bean
    public Exchange ordinaryExchange() {
        return ExchangeBuilder.directExchange(DeadLetterQueueExample.ORDINARY_EXCHANGE).durable(true).build();
    }

    @Bean
    public Binding ordinaryBinding() {
        return BindingBuilder.bind(ordinaryQueue()).to(ordinaryExchange()).with(DeadLetterQueueExample.ORDINARY_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(DeadLetterQueueExample.DEAD_LETTER_QUEUE).build();
    }

    @Bean
    public Exchange deadLetterExchange() {
        return ExchangeBuilder.directExchange(DeadLetterQueueExample.DEAD_LETTER_EXCHANGE).durable(true).build();
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DeadLetterQueueExample.DEAD_LETTER_ROUTING_KEY).noargs();
    }
}
