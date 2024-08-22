package com.wyl.rabbitmq.client.controller;

import com.wyl.common.config.RabbitRpcConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/19 17:30
 */
@Api(tags = "rpc-客户端api")
@RestController
@RequestMapping(value = "rpcClient")
public class RpcClientController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "客户端发送消息")
    @GetMapping(value = "sendMessage")
    public void sendMessage(String msg) {
        Message message = MessageBuilder.withBody(msg.getBytes(StandardCharsets.UTF_8)).build();
        Message result = rabbitTemplate.sendAndReceive(RabbitRpcConfig.EXCHANGE, RabbitRpcConfig.RPC_ROUTING_KEY, message);
        if(result != null) {
            String correlationId = message.getMessageProperties().getCorrelationId();
            String returnCorrelationId = result.getMessageProperties().getHeader("spring_returned_message_correlation");
            if(correlationId.equals(returnCorrelationId)) {
                System.out.println("已确认该消息由该客户端发送");
            }

            System.out.println(new String(result.getBody()));

        }
    }
}
