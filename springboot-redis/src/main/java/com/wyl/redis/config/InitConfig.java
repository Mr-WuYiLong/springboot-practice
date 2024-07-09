package com.wyl.redis.config;

import com.wyl.redis.service.impl.FullCityOperate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Description 公共初始化配置
 * @Author WuYiLong
 * @Date 2024/7/8 9:38
 */
@Slf4j
@Component
public class InitConfig implements ApplicationRunner {

    @Autowired
    private FullCityOperate fullCityDicService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("****************初始化*************");
    }
}
