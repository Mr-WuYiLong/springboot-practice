package com.wyl.redis.controller;

import com.wyl.redis.entity.FullCity;
import com.wyl.redis.service.FullCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description 
* @Author wuyilong
* @Date 2024-07-03
*/
@RestController
@RequestMapping("/fullCity")
public class FullCityController {

    @Autowired
    private FullCityService fullCityService;
}
