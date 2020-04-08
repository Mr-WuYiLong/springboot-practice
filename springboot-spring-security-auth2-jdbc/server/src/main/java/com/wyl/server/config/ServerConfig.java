package com.wyl.server.config;

import ch.qos.logback.core.db.DataSourceConnectionSource;
import ch.qos.logback.core.db.DriverManagerConnectionSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @ClassName ServerConfig
 * @Description
 * @Author yilongwu
 * @DATE 2020-04-07 10:09
 * @Version 1.0.0
 **/
@EnableAuthorizationServer
@Configuration
public class ServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsServiceBean;


    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

//    @Bean
//    public ClientDetailsService clientDetailsService() {
//        return new JdbcClientDetailsService(dataSource);
//    }



    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 需要允许和客户端认证,要不访问/oauth/token时会出现401
//        security.allowFormAuthenticationForClients();
        security
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //
        clients.inMemory()
                .withClient("client")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("authorization_code","refresh_token")
                .scopes("app")
                .redirectUris("http://wuyilong.cc")
                .resourceIds("wyl");

         // 保存oauth_client_details
//        clients.withClientDetails(clientDetailsService());

    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {


        endpoints// 保存oauth_access_token，oauth_refresh_token
                .tokenStore(tokenStore()).userDetailsService(userDetailsServiceBean)
                // 保存auth_code
                .authorizationCodeServices(authorizationCodeServices())
                // 保存oauth_approvals
                .approvalStore(approvalStore());
    }
}
