package com.wyl.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
