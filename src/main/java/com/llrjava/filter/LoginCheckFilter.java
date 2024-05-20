package com.llrjava.filter;

import com.alibaba.fastjson.JSONObject;
import com.llrjava.pojo.Result;
import com.llrjava.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.util.StringUtil;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //先将其强转为http的请求响应接口对象做接收
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        HttpServletResponse resp= (HttpServletResponse) servletResponse;
        //1,获取请求url
        String url=req.getRequestURL().toString();
        log.info("请求的url:{}",url);
        //2.判断靖求ur1中是否包含login，如果包含，说明是登录操作，放行。
        if(url.contains("login")){
            log.info("登录操作,放行");
            filterChain.doFilter(servletRequest,servletResponse);
            //添加return让代码不再向下执行
            return;
        }
        //3.获取请求头中的令牌(token)
        String jwt = req.getHeader("token");
        //4.判断令牌是否存在、如果不存在，返回错误结果(未登录)
        //调用一个字符串工具类来查看这个字符串是否为空(也就是是是否有长度)
        if (!StringUtils.hasLength(jwt)){
            log.info("请求头token空,返回未登录的信息");
            //创建返回数据对象进行返回
            Result error = Result.error("NOT_LOGIN");
            //因为现在不是在controller接口中所以我们手动转换对象变为JSON格式
            //这里我们引入阿里巴巴的依工具包
            String notLogin = JSONObject.toJSONString(error);
            //通过ServletResponse对象进行http数据返回响应
            resp.getWriter().write(notLogin);
            //结束方法
            return;

        }
        //5.解析令牌，如果解析失败，返回错误结果(未登录)
        try {
            JwtUtils.parseJWT(jwt);

        } catch (Exception e) //jwt解析失败
        {
            e.printStackTrace();
            log.info("解析令牌失败,返回未登录错误信息");
            //创建返回数据对象进行返回
            Result error = Result.error("NOT_LOGIN");
            //和之前返回错误消息一样进行json转换
            String notLogin = JSONObject.toJSONString(error);
            //通过ServletResponse对象进行http数据返回响应
            resp.getWriter().write(notLogin);
            //结束方法
            return;
        }

        //6.令牌没有报错就放行
        log.info("令牌合法,放行");
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
