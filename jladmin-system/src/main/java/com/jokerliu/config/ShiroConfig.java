package com.jokerliu.config;

import com.jokerliu.config.shiro.CredentialsMatcher;
import com.jokerliu.config.shiro.ShiroRealm;
import com.jokerliu.config.shiro.ShiroSessionManager;
import com.jokerliu.config.shiro.ShiroUserFilter;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author JokerLiu
 * @create 2018-12-29 10:36
 * @desc ShiroConfig
 **/
@Configuration
public class ShiroConfig {
    /**
     * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
     * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        // 没有登陆的用户只能访问登陆页面，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/common/kickout");
//        shiroFilterFactoryBean.setLoginUrl("/common/unauth");
        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/auth/index");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("common/unauth");

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        // 注意这里不要用Bean的方式，否则会报错
        filters.put("authc", new ShiroUserFilter());
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<>();
        //注意过滤器配置顺序 不能颠倒
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        filterChainDefinitionManager.put("/logout", "logout");
        // 公共请求
        filterChainDefinitionManager.put("/common/**", "anon");
        // 测试方法
        filterChainDefinitionManager.put("/test/**", "anon");
        // 静态资源
        filterChainDefinitionManager.put("/static/**", "anon");
        filterChainDefinitionManager.put("/homepageadmin/**", "anon");

        // 登录方法
        filterChainDefinitionManager.put("/login", "anon");
//        filterChainDefinitionManager.put("/admin/login*", "anon"); // 表示可以匿名访问
        // 注册方法
        filterChainDefinitionManager.put("/register", "anon");
//        filterChainDefinitionManager.put("/admin/register*", "anon"); // 表示可以匿名访问
//        filterChainDefinitionManager.put("/user/**", "authc,roles[ROLE_USER]");//用户为ROLE_USER 角色可以访问。由用户角色控制用户行为。
        //需要admin的操作
//        filterChainDefinitionManager.put("/admin/**", "authc,roles[admin]");
        filterChainDefinitionManager.put("/admin/information/getOne", "anon");
        filterChainDefinitionManager.put("/admin/information/page", "anon");

        filterChainDefinitionManager.put("/admin/pictureManage/page", "anon");
        filterChainDefinitionManager.put("/admin/pictureManage/getOne", "anon");

        filterChainDefinitionManager.put("/admin/leavemes/save", "anon");

        filterChainDefinitionManager.put("/admin/**", "authc");
        //swagger2
        filterChainDefinitionManager.put("/swagger-ui.html", "anon");
        filterChainDefinitionManager.put("/swagger-resources/**", "anon");
        filterChainDefinitionManager.put("/v2/**", "anon");
        filterChainDefinitionManager.put("/webjars/**", "anon");


        filterChainDefinitionManager.put("/**", "authc");
//        filterChainDefinitionManager.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
//
//
//        shiroFilterFactoryBean.setSuccessUrl("/");
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        return shiroFilterFactoryBean;
    }

    /**
     * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
     * //
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(myShiroRealm());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(getEhCacheManager());
        securityManager.setSessionManager(shiroSessionManager());
        return securityManager;
    }

    /**
     * 第一步获得realm
     */
    @Bean
    public ShiroRealm myShiroRealm() {
        ShiroRealm myShiroRealm = new ShiroRealm();
        myShiroRealm.setCredentialsMatcher(credentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 设置自己加密方式
     * @return
     */

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    /**
     * 第二步获得cache管理器
     */


    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }
//       @Bean
//         public EhCacheManager ehCacheManager() {
//             EhCacheManager em = new EhCacheManager();
//              //将ehcacheManager转换成shiro包装后的ehcacheManager对象
//      //        em.setCacheManager(cacheManager);
//              em.setCacheManagerConfigFile("classpath:ehcache.xml");
//              return em;
//          }

    //第三步获得session管理器

    /**
     * shiro session的管理
     */
    @Bean
    public ShiroSessionManager shiroSessionManager() {
        ShiroSessionManager sessionManager = new ShiroSessionManager();

        SimpleCookie simpleCookie = new SimpleCookie("Token");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(false);

        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setSessionIdCookieEnabled(false);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookie(simpleCookie);
        return sessionManager;
    }

    @Bean
    public SessionDAO sessionDAO() {
        return new MemorySessionDAO();
    }


    /**
     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aASA = new AuthorizationAttributeSourceAdvisor();
        aASA.setSecurityManager(securityManager());
        return aASA;
    }


}

