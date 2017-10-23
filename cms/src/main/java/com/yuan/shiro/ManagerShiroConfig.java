package com.yuan.shiro;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author joryun ON 2017/10/22.
 */
@Configuration
public class ManagerShiroConfig {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        Map<String, String> filterChainDefinitionMapping = shiroFilter.getFilterChainDefinitionMap();
        swaggerFilterChain(filterChainDefinitionMapping);
        filterChainDefinitionMapping.put("/", "cors");
        filterChainDefinitionMapping.put("/api/admin/manager/login","cors");
        filterChainDefinitionMapping.put("/api/admin/picCaptcha/getPicCaptcha","cors");
        filterChainDefinitionMapping.put("/**", "cors,admin");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
        shiroFilter.setSecurityManager(securityManager);
        Map<String, Filter> filters = new HashMap<>();
        filters.put("admin", new ManagerFilter());
        filters.put("cors", new CorsFilter());
        shiroFilter.setFilters(filters);
        return shiroFilter;
    }

    private void swaggerFilterChain(Map<String, String> filterChainDefinitionMapping) {
        filterChainDefinitionMapping.put("/v2/api-docs", "anon");
        filterChainDefinitionMapping.put("/configuration/**", "anon");
        filterChainDefinitionMapping.put("/webjars/**", "anon");
        filterChainDefinitionMapping.put("/swagger**", "anon");
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager(ManagerRealm cmsRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(cmsRealm);
//        securityManager.setCacheManager(new MemoryConstrainedCacheManager());
        return securityManager;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthorizationAttributeSourceAdvisor advisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "passwordService")
    public PasswordService passwordService() {
        return new DefaultPasswordService();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
