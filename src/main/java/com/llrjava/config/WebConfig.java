package com.llrjava.config;

import com.llrjava.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//配置类
public class WebConfig implements WebMvcConfigurer {
    //将拦截类注入进来
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;
    @Override//重写其中的注册拦截器的方法
    public void addInterceptors(InterceptorRegistry registry) {
        //调用注册方法并指定拦截什么url响应,这里拦截所有的url响应
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**");
    }
}
