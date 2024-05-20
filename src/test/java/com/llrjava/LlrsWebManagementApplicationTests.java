package com.llrjava;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//这个标签加或不加取决于此时测试方法需不需要用到springboot框架

//@SpringBootTest
class LlrsWebManagementApplicationTests {

    @Test
    void contextLoads() {
    }
//    生成JWT
    @Test
    public void testGenJwt(){
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",1);
        claims.put("name","Tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "llrjava") //签名算法
                .setClaims(claims) //自定义内容
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();//设置有效期为一小时
        System.out.println(jwt);
    }

    //解析JWT令牌
    @Test
    public void testParseJwt(){
        Claims claims = (Claims) Jwts.parser()
                .setSigningKey("llrjava")
                .parse("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVG9tIiwiaWQiOjEsImV4cCI6MTcxNDg5MjA4OH0.cZzGXt3jqgbYxtwOBXH6b4iY2JgbS92SLiAoHHf7XAY")
                .getBody();

        System.out.println(claims);
    }
}
