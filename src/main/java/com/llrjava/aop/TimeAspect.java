package com.llrjava.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
//@Aspect//aop类
public class TimeAspect {

    //定义一个方法来执行通用逻辑,也就是记录时间的模板逻辑

    //切入点表达式
   @Around("execution(* com.llrjava.Service.*Service.*(..))")
    //joinPoint这个参数封装了调用的原始方法的所有信息(反射技术)
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //1,记录开始时间
        long begin = System.currentTimeMillis();

        //2,调用原始方法运行,方法的返回值指原始方法的返回值
        Object result = joinPoint.proceed();


        //3,记录结束时间,并计算方法执行耗时
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + "方法执行时间: {}ms", end - begin);

        //返回原始方法运行结果
        return result;
    }

}
