package com.wyl.mybatis.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2025/3/16 19:42
 */
//@Configuration
public class MybatisConfig {
    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "pageNum=currentPage;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}