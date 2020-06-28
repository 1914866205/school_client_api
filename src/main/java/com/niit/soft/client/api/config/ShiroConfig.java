package com.niit.soft.client.api.config;

import com.niit.soft.client.api.filter.JwtFilter;
import com.niit.soft.client.api.service.impl.Realm;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroConfig:shiro 配置类,配置哪些拦截,哪些不拦截,哪些授权等等各种配置都在这里
 *
 * @Author 涛涛
 * @Date 2020/5/24 9:55
 * @Version 1.0
 **/
@Configuration
public class ShiroConfig {

    /**
     * 注入安全过滤器
     *
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //配置不会被拦截的链接，顺序配置
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/code/login", "anon");
        filterChainDefinitionMap.put("/user/password", "anon");
        filterChainDefinitionMap.put("/sendCode", "anon");
        filterChainDefinitionMap.put("/verifyCode", "anon");

        //swagger文档拦截器配置
        //swagger接口权限 开放
        filterChainDefinitionMap.put("/doc.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");

        //job
        filterChainDefinitionMap.put("/company/**", "anon");
        filterChainDefinitionMap.put("/job/**", "anon");
        filterChainDefinitionMap.put("/jobType/**", "anon");
        filterChainDefinitionMap.put("/partJob/**", "anon");

        filterChainDefinitionMap.put("/show", "anon");
        filterChainDefinitionMap.put("/qq/oauth", "anon");
        filterChainDefinitionMap.put("/connect", "anon");
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/home", "anon");
        filterChainDefinitionMap.put("/wechat/openid", "anon");
        filterChainDefinitionMap.put("/alipay/toPay", "anon");
        filterChainDefinitionMap.put("/alipay/notify_url", "anon");
        filterChainDefinitionMap.put("/alipay/return_url", "anon");

        filterChainDefinitionMap.put("/dynamic/**", "anon");

        //添加自己的过滤器并取名为jwt
        Map<String, Filter> filterMap = new HashedMap();
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        filterChainDefinitionMap.put("/**", "jwt");
        //未授权页面  此处未设置
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 注入安全管理器
     *
     * @param realm
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        //关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }
}
