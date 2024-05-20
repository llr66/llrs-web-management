package com.llrjava.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//配置拦截器,并表明其拦截的url路径
//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    //初始化方法,只调用一次(在启动服务端的时候执行)
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init 初始化方法执行了");
    }
    //拦截到请求之后调用,调用多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //放行前的逻辑
        System.out.println("Demo拦截到了请求...放行前逻辑");
        //放行操作,这几个参数都是方法接收到的参数
        filterChain.doFilter(servletRequest,servletResponse);
        //放行后的逻辑
        System.out.println("Demo放行后逻辑");

    }
    //销毁方法,只调用一次(再关闭服务器的时候执行)
    @Override
    public void destroy() {
        System.out.println("destroy 销毁方法执行了");
    }
}
