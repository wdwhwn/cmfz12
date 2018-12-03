package com.baizhi.shiro.conf;

import com.baizhi.shiro.realm.MyRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wdwhwn on 2018/11/2.
 */
@Configuration
public class ShiroFilter {

//    管理工厂类
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, String> map = new HashMap<>();
        //所有请求都需要验证访问
        map.put("/login.jsp", "anon");
        map.put("/user/login", "anon");
        map.put("/defaultKaptcha", "anon");
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/main/login.jsp");
//        将安全管理器设置到工厂中
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

//    管理securityMangager对象
    @Bean
    public SecurityManager getSecurityManager(Realm realm,CacheManager cacheManager){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        defaultWebSecurityManager.setCacheManager(cacheManager);
        return defaultWebSecurityManager;
    }

//    管理自定义realm
    @Bean
    public Realm getRealm(CredentialsMatcher hashedCredentialsMatcher){
        MyRealm myRealm=new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
            return myRealm;
    }

//    加密
    @Bean
    public CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

//  管理缓存对象
    @Bean
    public CacheManager getCacheManager(){
        return new EhCacheManager();
    }

}
