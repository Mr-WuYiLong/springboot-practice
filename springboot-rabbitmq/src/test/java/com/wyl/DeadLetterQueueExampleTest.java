package com.wyl;

import com.wyl.rabbitmq.example.DeadLetterQueueExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/20 10:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeadLetterQueueExampleTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testDeadLetterQueueExample() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            String msg = "java是一门计算机语言"+i;
//            rabbitTemplate.convertAndSend(DeadLetterQueueExample.ORDINARY_EXCHANGE,DeadLetterQueueExample.ORDINARY_ROUTING_KEY,msg);

            Message message = new Message(msg.getBytes(StandardCharsets.UTF_8));
//            message.getMessageProperties().setExpiration("5000"); // 如果消息设置了过期时间，队列就不用设置了.ttl(5000)
            rabbitTemplate.send(DeadLetterQueueExample.ORDINARY_EXCHANGE,DeadLetterQueueExample.ORDINARY_ROUTING_KEY,message);
        }
        TimeUnit.SECONDS.sleep(20);

    }
}
