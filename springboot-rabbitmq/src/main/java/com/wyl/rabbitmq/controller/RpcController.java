package com.wyl.rabbitmq.controller;

import com.wyl.common.bean.ResponseData;
import com.wyl.common.config.RabbitRpcConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/19 12:12
 */
@Api(tags = "rabbitmq-api")
@RestController
@RequestMapping(value = "rpc")
public class RpcController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "生产者")
    @GetMapping(value = "producer")
    public ResponseData producer(String msg) {
        Message message = MessageBuilder.withBody(msg.getBytes(StandardCharsets.UTF_8)).build();
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        Message message1 = rabbitTemplate.sendAndReceive(RabbitRpcConfig.EXCHANGE, RabbitRpcConfig.RPC_ROUTING_KEY, message,correlationData);
        System.out.println(message1.getBody());
        return ResponseData.success();
    }
}
