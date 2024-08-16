package com.wyl;

import com.wyl.rabbitmq.example.SixModeExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

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
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());
        rabbitTemplate.convertSendAndReceive(SixModeExample.RPC_EXCHANGE,SixModeExample.ROUTING_KEY,"hello world,rpc",correlationData);
    }
}
