package com.wyl.redis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/3 17:23
 */
@Slf4j
@Component
public class DictionaryService implements ApplicationContextAware {

    @Autowired
    private FullCityDic fullCityDic;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("****************字典初始化*************");
        fullCityDic.list();
    }
}
