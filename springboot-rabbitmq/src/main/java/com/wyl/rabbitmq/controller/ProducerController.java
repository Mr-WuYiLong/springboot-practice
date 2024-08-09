package com.wyl.rabbitmq.controller;

import com.wyl.common.bean.ResponseData;
import com.wyl.rabbitmq.service.ProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/8/2 14:30
 */
@Api(tags = "rabbitmq-api")
@RestController
@RequestMapping(value = "producer")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @ApiOperation(value = "简单消息")
    @PostMapping(value = "simpleMessage")
    private ResponseData simpleMessage(String message) {
        producerService.simpleMessage(message);
        return ResponseData.success();
    }
}
