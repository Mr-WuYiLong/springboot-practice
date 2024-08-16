package com.wyl;

import com.wyl.rabbitmq.example.FiveModeExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/16 13:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FiveModeExampleTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testFiveMode()  {
        rabbitTemplate.convertAndSend(FiveModeExample.EXCHANGE,"333.a.6666","测试a");
        rabbitTemplate.convertAndSend(FiveModeExample.EXCHANGE,"333.666.b","测试b");
        rabbitTemplate.convertAndSend(FiveModeExample.EXCHANGE,"c.3333.666.9999","测试c");

    }
}
