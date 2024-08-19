package com.wyl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/16 14:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SixModeExampleTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSixMode() {
//        String msg = "hello,world";
//        Message message = MessageBuilder.withBody(msg.getBytes(StandardCharsets.UTF_8)).build();
//
//        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
//        Message message1 = rabbitTemplate.sendAndReceive(SixModeExample.EXCHANGE, SixModeExample.RPC_ROUTING_KEY, message);
//        System.out.println(message1.getBody());
    }
}
