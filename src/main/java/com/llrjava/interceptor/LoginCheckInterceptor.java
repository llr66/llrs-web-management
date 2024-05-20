package com.llrjava.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.llrjava.pojo.Result;
import com.llrjava.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//目标资源方法运行前运行，返问true:放行，返回false，不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle方法运行了");
        //1,获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url:{}",url);
        //2.判断靖求ur1中是否包含login，如果包含，说明是登录操作，放行。
        if (url.contains("login")){
            log.info("登录操作,放行");
            return true;
        }
        //3.获取请求头中的令牌(token)
        String jwt = request.getHeader("token");
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
            response.getWriter().write(notLogin);
            return false;
        }
        try {
            JwtUtils.parseJWT(jwt);
        }        //5.解析令牌，如果解析失败，返回错误结果(未登录)
        catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败,返回未登录错误信息");
            //创建返回数据对象进行返回
            Result error = Result.error("NOT_LOGIN");
            //因为现在不是在controller接口中所以我们手动转换对象变为JSON格式
            //这里我们引入阿里巴巴的依工具包
            String notLogin = JSONObject.toJSONString(error);
            //通过ServletResponse对象进行http数据返回响应
            response.getWriter().write(notLogin);

            return false;
        }
        //6.令牌没有报错就放行
        log.info("令牌合法,放行");
        return true;
    }

    @Override//目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle方法运行了");
    }

    @Override//视图渲染完毕后允许,最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion方法运行了");
    }
}
