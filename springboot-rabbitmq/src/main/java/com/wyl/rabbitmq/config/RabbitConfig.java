package com.wyl.rabbitmq.config;

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



    @Bean
    public Queue simpleQueue() {
        Queue build = QueueBuilder.durable("simple-queue").build();
        return build;
    }

    @Bean
    public Exchange directExchange() {
        Exchange build = ExchangeBuilder.directExchange("direct").durable(true).build();
        return build;
    }

    @Bean
    public Binding bindingDirectExchange() {
        Binding simple = BindingBuilder.bind(simpleQueue()).to(directExchange()).with("direct-key-1").noargs();
        return simple;
    }

    @Bean
    public Queue fanoutQueue1() {
        Queue build = QueueBuilder.durable("fanoutQueue1").build();
        return build;
    }

    @Bean
    public Queue fanoutQueue2() {
        Queue build = QueueBuilder.durable("fanoutQueue2").build();
        return build;
    }

    @Bean
    public Queue fanoutQueue3() {
        Queue build = QueueBuilder.durable("fanoutQueue3").build();
        return build;
    }


    @Bean
    public Exchange fanoutExchange() {
        Exchange build = ExchangeBuilder.fanoutExchange("fanout").durable(true).build();
        return build;
    }

    @Bean
    public Binding bindingFanoutExchange1() {
        Binding binding = BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange()).with("").noargs();
        return binding;
    }

    @Bean
    public Binding bindingFanoutExchange2() {
        Binding binding = BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange()).with("").noargs();
        return binding;
    }

    @Bean
    public Binding bindingFanoutExchange3() {
        Binding binding = BindingBuilder.bind(fanoutQueue3()).to(fanoutExchange()).with("").noargs();
        return binding;
    }

    @Bean
    public Exchange topicExchange() {
        Exchange topic = ExchangeBuilder.topicExchange("topic").durable(true).build();
        return topic;
    }


    @Bean
    public Binding binding() {
        Binding binding = BindingBuilder.bind(simpleQueue()).to(topicExchange()).with("*.top").noargs();
        return binding;
    }


    /*************************************测试死信队列********************************************************/

    @Bean
    public Queue sendQueue() {
        return QueueBuilder.durable("sendQueue").ttl(5000).deadLetterExchange("deadExchange").deadLetterRoutingKey("dead-key").build();
    }

    @Bean
    public Exchange sendExchange(){
        return ExchangeBuilder.directExchange("sendExchange").build();
    }

    @Bean
    public Binding sendBinding() {
        return BindingBuilder.bind(sendQueue()).to(sendExchange()).with("send-key").noargs();
    }

    @Bean
    public Queue deadQueue() {
        return QueueBuilder.durable("deadQueue").build();
    }

    @Bean
    public Exchange deadExchange() {
        return ExchangeBuilder.directExchange("deadExchange").build();
    }

    @Bean
    public Binding  deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("dead-key").noargs();
    }



}
