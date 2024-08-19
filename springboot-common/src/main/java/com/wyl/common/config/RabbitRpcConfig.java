package com.wyl.common.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/19 17:24
 */
@Configuration
public class RabbitRpcConfig {

    public static final String REPLY_QUEUE = "sixModeReplyQueue";
    public static final String RPC_QUEUE = "sixModeRpcQueue";
    public static final String EXCHANGE = "sixModeExchange";
    public static final String RPC_ROUTING_KEY = "sixModeRpcRoutingKey";
    public static final String REPLY_ROUTING_KEY = "sixModeReplyRoutingKey";

    @Bean
    public Queue replyQueue() {
        return QueueBuilder.durable(REPLY_QUEUE).build();
    }

    @Bean
    public Exchange rpcExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE).build();
    }

    @Bean
    public Binding replyBinding() {
        return BindingBuilder.bind(replyQueue()).to(rpcExchange()).with(REPLY_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue rpcQueue() {
        return QueueBuilder.durable(RPC_QUEUE).build();
    }

    @Bean
    public Binding rpcBinding() {
        return BindingBuilder.bind(rpcQueue()).to(rpcExchange()).with(RPC_ROUTING_KEY).noargs();
    }


}
