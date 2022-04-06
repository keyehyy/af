package com.gt.af.config;

import com.gt.af.security.CustomAccessDeniedHandler;
import com.gt.af.security.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Resource
    private AuthenticationSuccessHandler loginSuccessHandler; //认证成功结果处理器
    @Resource
    private AuthenticationFailureHandler loginFailureHandler; //认证失败结果处理器

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * 用户认证
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());//使用BCryptPasswordEncoder进行加密
    }



    /**
     * 基于json用户登录判断及响应
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()//所有的路径都是登录后即可访问
                .and()
                .formLogin().loginPage("/login")//如果是未登录的会自动跳到该接口（根据需要自己实现，返回页面或返回json）
                .loginProcessingUrl("/login")//发起登录请求的接口
                .usernameParameter("username")//设置登录请求接口的参数（用户名）
                .passwordParameter("password")//设置登录请求接口的参数（密码）
                .successHandler(loginSuccessHandler)//使用自定义的成功结果处理器
                .failureHandler(loginFailureHandler)//使用自定义失败的结果处理器
                .permitAll()
                .and()
                .logout()//注销登录接口（/logout）
                .logoutUrl("/logout")
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .permitAll()
                .and().csrf().disable().exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler());//用户权限不足时的处理
    }

//        http.headers().frameOptions().disable();//开启运行iframe嵌套页面

//        http//1、配置权限认证
//                .authorizeRequests()
//                //配置不拦截路由
//                .antMatchers("/500").permitAll()
//                .antMatchers("/403").permitAll()
//                .antMatchers("/404").permitAll()
//                .antMatchers("/login").permitAll()
//                .anyRequest() //任何其它请求
//                .authenticated() //都需要身份认证
//                .and()
//                //2、登录配置表单认证方式
//                .formLogin()
//                .loginPage("/login")//自定义登录页面的url
//                .usernameParameter("username")//设置登录账号参数，与表单参数一致
//                .passwordParameter("password")//设置登录密码参数，与表单参数一致
//                // 告诉Spring Security在发送指定路径时处理提交的凭证，默认情况下，将用户重定向回用户来自的页面。登录表单form中action的地址，也就是处理认证请求的路径，
//                // 只要保持表单中action和HttpSecurity里配置的loginProcessingUrl一致就可以了，也不用自己去处理，它不会将请求传递给Spring MVC和控制器，所以我们就不需要自己再去写一个/user/login的控制器接口了
//                .loginProcessingUrl("/user/login")//配置默认登录入口
//                .defaultSuccessUrl("/index")//登录成功后默认的跳转页面路径
//                .failureUrl("/login?error=true")
//                .successHandler(loginSuccessHandler)//使用自定义的成功结果处理器
//                .failureHandler(loginFailureHandler)//使用自定义失败的结果处理器
//                .and()
//                //3、注销
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
//                .permitAll()
//                .and()
//                //4、session管理
//                .sessionManagement()
//                .invalidSessionUrl("/login") //失效后跳转到登陆页面
//                //单用户登录，如果有一个登录了，同一个用户在其他地方登录将前一个剔除下线
//                //.maximumSessions(1).expiredSessionStrategy(expiredSessionStrategy())
//                //单用户登录，如果有一个登录了，同一个用户在其他地方不能登录
//                //.maximumSessions(1).maxSessionsPreventsLogin(true) ;
//                .and()
//                //5、禁用跨站csrf攻击防御
//                .csrf()
//                .disable();
//    }
//
////    @Override
////    public void configure(WebSecurity web) throws Exception {
////        //配置静态文件不需要认证
////        web.ignoring().antMatchers("/static/**");
////    }
//
//    /**
//     * 指定加密方式
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        // 使用BCrypt加密密码
//        return new BCryptPasswordEncoder();
//    }
}