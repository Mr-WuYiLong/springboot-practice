package com.wyl;

import com.wyl.rabbitmq.example.SecondModeExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/16 10:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SecondModeExampleTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSecondMode() {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(SecondModeExample.EXCHANGE,SecondModeExample.ROUTE_KEY,"面包"+i);
        }
    }
}
