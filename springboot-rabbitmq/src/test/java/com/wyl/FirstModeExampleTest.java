package com.wyl;

import com.wyl.rabbitmq.example.FirstModeExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/16 10:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstModeExampleTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testFirstMode(){
        rabbitTemplate.convertAndSend(FirstModeExample.EXCHANGE,FirstModeExample.ROUTE_KEY,"Hello World!");
    }

}
