package com.llrjava.exception;

import com.llrjava.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
    全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//表明捕获所有的异常
    public Result ex(Exception ex){
        log.info("异常被捕获");
        ex.printStackTrace();
        //响应统一错误结果
        return Result.error("对不起操作错误,请联系管理员");
    }
}
