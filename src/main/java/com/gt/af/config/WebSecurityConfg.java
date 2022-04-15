package com.gt.af.config;

import com.gt.af.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;


/**
 * @desc SpringSecurity安全框架配置
 * @author zhukeyan
 * @date
 */
@Configuration
@EnableWebSecurity//开启Spring Security的功能
//prePostEnabled属性决定Spring Security在接口前注解是否可用@PreAuthorize,@PostAuthorize等注解,设置为true,会拦截加了这些注解的接口
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfg extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler; //认证成功结果处理器
    @Autowired
    private AuthenticationFailureHandler loginFailureHandler; //认证失败结果处理器
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;// 会话超时
    //访问决策管理器
    @Autowired
    CustomAccessDecisionManager accessDecisionManager;
    //实现权限拦截
    @Autowired
    CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;
    @Autowired
    private CustomizeAbstractSecurityInterceptor securityInterceptor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(accessDecisionManager);//决策管理器
                        o.setSecurityMetadataSource(securityMetadataSource);//安全元数据源
                        return o;
                    }
                })
                .and()
                .formLogin()
                .successHandler(loginSuccessHandler)//使用自定义的成功结果处理器
                .failureHandler(loginFailureHandler)//使用自定义失败的结果处理器
                .permitAll()
                .and()
                .logout()//注销登录接口（/logout）
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .permitAll()
                .and().csrf().disable().exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())//未认证权限不足
                .accessDeniedHandler(new CustomAccessDeniedHandler())//用户权限不足时的处理
                .and()//会话管理
                .sessionManagement()
                .maximumSessions(1)//同一账号同时登录最大用户数
                .expiredSessionStrategy(sessionInformationExpiredStrategy);//会话失效(账号被挤下线)处理逻辑
        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
    }

    /**
     * @desc 用户认证
     * @author zhukeyan
     * @date 2022/4/8
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    /**
     * @desc 放开静态资源
     * @author zhukeyan
     * @date 2022/4/8
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();
    }
}