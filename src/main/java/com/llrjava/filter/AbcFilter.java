package com.llrjava.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class AbcFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //放行前的逻辑
        System.out.println("ABC拦截到了请求...放行前逻辑");
        //放行操作,这几个参数都是方法接收到的参数
        filterChain.doFilter(servletRequest,servletResponse);
        //放行后的逻辑
        System.out.println("ABC放行后逻辑");
    }
}
