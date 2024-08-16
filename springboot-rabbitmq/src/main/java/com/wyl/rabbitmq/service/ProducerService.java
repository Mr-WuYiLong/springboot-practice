package com.wyl.rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/2 14:09
 */
@Service
public class ProducerService {

    private static final Logger log = LoggerFactory.getLogger(ProducerService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void simpleMessage(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("direct","direct-key-1",message,correlationData);
    }

    public void moreQueue(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("fanout","",message,correlationData);
    }

    @Transactional(rollbackFor = Exception.class)
    public void moreQueue1(String message) throws InterruptedException {

        rabbitTemplate.convertAndSend("direct","direct-key-1",message);
        log.info("*********消息已发送**********");
        TimeUnit.SECONDS.sleep(10);
    }

    public void moreQueue2(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("direct","direct-key-1",message,correlationData);
    }

    public void sendTopicMessage(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("topic","a.top",message,correlationData);
    }

    public void sendQueue(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("sendExchange","send-key",message,correlationData);
    }

//    @Scheduled(cron = "0/5 * * * * ?")
    public void scheduleTask() throws InterruptedException {
//        for (int i = 0;i<100;i++) {
//            simpleMessage("hello,world"+i);
//            moreQueue("moreQueue"+i);
//            moreQueue1("moreQueue1"+i);
//            moreQueue2("moreQueue2"+i);
//            sendTopicMessage("topic"+i);
//            sendQueue("hello");
//        }
        moreQueue1("事务-hello");
    }





}
