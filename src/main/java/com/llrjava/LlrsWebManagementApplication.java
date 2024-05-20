package com.llrjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class LlrsWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LlrsWebManagementApplication.class, args);
    }

}
