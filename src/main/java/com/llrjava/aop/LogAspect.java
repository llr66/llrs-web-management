package com.llrjava.aop;


import com.alibaba.fastjson.JSONObject;
import com.llrjava.mapper.OperateLogMapper;
import com.llrjava.pojo.OperateLog;
import com.llrjava.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {
    //注入操作数据库记录日志的mapper类
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Around("@annotation(com.llrjava.anno.OperateLog)")
    public Object OperateLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //1获取操作人
        //获取JWT令牌并进行解码
        String JWT = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJWT(JWT);
        Integer operateUser = (Integer) claims.get("id");
        log.info("记录了操作人id: {}",operateUser);

        //2,获取操作时间,并记录此时毫秒值以便计算运行时间
        LocalDateTime operateTime = LocalDateTime.now();
        long begin = System.currentTimeMillis();
        log.info("记录了操作时间: {}",operateTime);
        //3,获取执行方法的全类名
        String className = joinPoint.getTarget().getClass().getName();
        log.info("记录了方法的全类名: {}",className);
        //4,执行方法名
        String methodName = joinPoint.getSignature().getName();
        log.info("记录了执行方法名: {}",methodName);
        //5,方法运行时参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        log.info("记录了方法运行时参数: {}",methodParams);
        //6,放行方法获取返回值
        Object re = joinPoint.proceed();
        String returnValue = JSONObject.toJSONString(re);
        if (returnValue.length()>2000){
            returnValue=returnValue.substring(0,1999);
        }
        log.info("记录了方法获取返回值: {}",returnValue);

        //7,方法执行时间
        long end = System.currentTimeMillis();
        Long costTime=end-begin;
        log.info("记录了方法执行时间: {}ms",costTime);

        //创建操作日志记录实体类
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        //调用mapper接口类的方法将数据记录到数据库表中
        operateLogMapper.insert(operateLog);
        return re;
    }
}
