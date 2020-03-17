package com.wyl.springbootshiro.shiro;

//import com.wyl.springbootshiro.filter.JWTFilter;
import com.wyl.springbootshiro.filter.JWTFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig shiro的配置文件
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-11 15:34
 * @Version 1.0.0
 **/
@Configuration
@Slf4j
public class ShiroConfig {

    /**
     * 把自定义的CustomRealm注入到spring容器中
     * @return
     */
    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
//        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;

    }

    /**
     * 注入securityManager
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());

        // 关闭session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    // 设置用于匹配密码的CredentialsMatcher
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        credentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);  // 散列算法，这里使用更安全的sha256算法
//        credentialsMatcher.setStoredCredentialsHexEncoded(false);  // 数据库存储的密码字段使用HEX还是BASE64方式加密
//        credentialsMatcher.setHashIterations(1);  // 散列迭代次数
//        return credentialsMatcher;
//    }

     //注入过滤器
//    @Bean
//    public JWTFilter jwtFilter() {
//        return new JWTFilter();
//    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 这个在做前后端分离时，如果你没有登录就访问其他的资源就会跳到这个设置的url
//        shiroFilterFactoryBean.setLoginUrl("/notLogin");

        // 没有权限时进行跳转(ps:在没有使用注解的情况下能自动捕获异常，并跳转到该指定的路径)
        //shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthorized");
        Map<String, Filter> objectObjectLinkedHashMap = new LinkedHashMap<>();
        objectObjectLinkedHashMap.put("jwt",new JWTFilter());
        // 设置拦截器
        // 为什么不用HashMap？因为HashMap是无序的，会导致部分路径无法拦截，时有时无
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 开放登录
        filterChainDefinitionMap.put("/login","anon");
        // 其余的需要进过滤器
        shiroFilterFactoryBean.setFilters(objectObjectLinkedHashMap);
        filterChainDefinitionMap.put("/**","jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
