package com.wyl;

import com.wyl.rabbitmq.example.ThirdModeExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/16 10:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThirdModeExampleTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testThirdMode() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(ThirdModeExample.EXCHANGE,"","面包");
        }
    }
}
