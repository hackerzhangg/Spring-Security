package com.bigjava18.springsecurityformlogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @Author zgp
 * @Since 2021 -05 -26 10 :36
 * @Description SpringSecurity配置类
 * @Configuration：配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 对客户端传过来的密码进行加密
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中配置用户名和密码
        //and:相当于一个结束标签
        auth.inMemoryAuthentication().withUser("zgp").password("9999").roles("admin");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略静态文件不拦截
        web.ignoring().antMatchers("/css/**","/js/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置自定义登录页
        //开启配置
        http.authorizeRequests()
                //所有请求都要认证之后才可以访问
                .anyRequest().authenticated()
                .and()
                //表单配置
                .formLogin()
                //配置登录页面
                .loginPage("/login.html")
                //配置登录接口，如果不配置，登录接口默认和loginPage("/login.html")一致
                .loginProcessingUrl("/doLogin")
                //自定义接收表单中的name属性 username password
                //.usernameParameter("username")
                //.passwordParameter("password")
                //登录成功后调转的页面,服务端调转
                .successForwardUrl("/success")
                //登录成功后调转的页面,重定向 如果配置为true 则和successForwardUrl效果相同
                //.defaultSuccessUrl("/scuuess",true)
                //.defaultSuccessUrl("/success")
                //和登录相关的页面都统统放行，不要拦截
                .permitAll()
                .and()
                //注销登录
                .logout()
                //默认logout,get请求
                //.logoutUrl("/logout")
                //注销登录Post请求
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                .logoutSuccessUrl("/login.html")
                //清除session 默认不配置也会
                .invalidateHttpSession(true)
                //清楚认证 默认不配置也会
                .clearAuthentication(true)
                .and()
                .csrf().disable();
    }
}
