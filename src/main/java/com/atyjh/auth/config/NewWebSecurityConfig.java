//package com.atyjh.auth.config;
//
//import com.atyjh.auth.custom.CustomMd5Password;
//import com.atyjh.auth.filter.TokenAuthenticationFilter;
//import com.atyjh.auth.filter.TokenLoginFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
///**
// * Copyright: Copyright(c) 2022 iwhalecloud
// * <p>
// * 类说明：TODO
// * <p>
// * 类名称: NewWebSecurityConfig.java
// *
// * @author huang.yijie
// * 时间: 2023/2/14 01:09
// * <p>
// * Modification History:
// * Date Author Version Description
// * ------------------------------------------------------------
// * @version v1.0.0
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class NewWebSecurityConfig {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private CustomMd5Password customMd5Password;
//
//    /**
//     * 认证管理器，登录的时候参数会传给 authenticationManager
//     *
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        // 这是配置的关键，决定哪些接口开启防护，哪些接口绕过防护
//        http
//            //关闭csrf
//            .csrf().disable()
//            // 开启跨域以便前端调用接口
//            .cors().and()
//            .authorizeRequests()
//            // 指定某些接口不需要通过验证即可访问。登陆接口肯定是不需要认证的
//            .antMatchers("/user/login").permitAll()
//            // 这里意思是其它所有接口需要认证才能访问
//            .anyRequest().authenticated()
//            .and()
//            //TokenAuthenticationFilter放到UsernamePasswordAuthenticationFilter的前面，这样做就是为了除了登录的时候去查询数据库外，其他时候都用token进行认证。
//            .addFilterBefore(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//            .addFilter(new TokenLoginFilter());
//
//        //禁用session
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        return http.build();
//    }
//
//
//    /**
//     * 配置路径放行规则
//     */
//    @Bean
//    WebSecurityCustomizer webSecurityCustomizer() {
//        return new WebSecurityCustomizer() {
//            @Override
//            public void customize(WebSecurity web) {
//                web.ignoring().antMatchers("/favicon.ico","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html");
//            }
//        };
//    }
//}
