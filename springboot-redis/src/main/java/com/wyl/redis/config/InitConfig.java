package com.wyl.redis.config;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.dynamic.datasource.support.ScriptRunner;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Description 公共初始化配置
 * @Author WuYiLong
 * @Date 2024/7/8 9:38
 */
@Slf4j
@ConditionalOnProperty(prefix = "init",value = "enabled",havingValue = "true")
@Component
public class InitConfig implements ApplicationRunner {

    @Autowired
    private DynamicDataSourceProperties dynamicDataSourceProperties;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("****************初始化数据库脚本开始*************");
        Map<String, DataSourceProperty> datasource = dynamicDataSourceProperties.getDatasource();
        DataSourceProperty master = datasource.get("master");
        DataSource build = DataSourceBuilder
                .create()
                .url(master.getUrl())
                .driverClassName(master.getDriverClassName())
                .password(master.getPassword())
                .type(master.getType())
                .username(master.getUsername())
                .build();

        ScriptRunner scriptRunner = new ScriptRunner(true, ";");
        scriptRunner.runScript(build,"classpath:/db/**");
        log.info("****************初始化数据库脚本结束*************");

    }
}
