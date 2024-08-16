package com.wyl.rabbitmq.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/16 14:31
 */
@Slf4j
@Service
public class SixModeExample {

    public static final String REPLY_QUEUE = "sixModeReplyQueue";
    public static final String RPC_QUEUE = "sixModeRpcQueue";
    public static final String RPC_EXCHANGE = "sixModeRpcExchange";
    public static final String ROUTING_KEY = "sixModeRoutingKey";

//    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = RPC_QUEUE), exchange = @Exchange(value = RPC_EXCHANGE),key = ROUTING_KEY))
//    public void consumer(String data, Channel channel, Message message) throws IOException {
//        log.info("consumer->{}", data,data);
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//    }
}
