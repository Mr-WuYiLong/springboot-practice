package com.wyl.rabbitmq.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.BindingFactoryBean;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/2 14:43
 */
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setConfirmCallback(new DefaultConfirmCallback());
        rabbitTemplate.setReturnsCallback(new DefaultReturnsCallback());
        rabbitTemplate.setMandatory(true); //设置为true，才会触发DefaultReturnsCallback方法
        return rabbitTemplate;
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



}
