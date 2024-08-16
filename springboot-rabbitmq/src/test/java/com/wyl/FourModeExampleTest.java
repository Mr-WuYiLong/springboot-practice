package com.wyl;

import com.wyl.rabbitmq.example.FourModeExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/16 10:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FourModeExampleTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testFourMode() {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(FourModeExample.EXCHANGE, FourModeExample.ROUTING_KEY1, "面包"+i);
        }

        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(FourModeExample.EXCHANGE, FourModeExample.ROUTING_KEY2, "水"+i);
        }

        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(FourModeExample.EXCHANGE,FourModeExample.ROUTING_KEY3,"小吃");
        }
    }
}
