package com.llrjava.controller;

import com.llrjava.Service.EmpService;
import com.llrjava.pojo.Emp;
import com.llrjava.pojo.Result;
import com.llrjava.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
//登录校验
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录{}",emp);
        Emp e=empService.login(emp);
       //登录成功,生成令牌,下发令牌
        if(e!=null){
            //将员工共数据封装到map集合当中
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());
            //将装有员工数据的map集合封装到令牌中去
            String jwt = JwtUtils.generateJwt(claims);  //jwt包含了当前登录的员工信息
            return Result.success(jwt);
        }
        //登陆失败,返回错误信息
        return Result.error("用户名或密码错误");
    }
}
