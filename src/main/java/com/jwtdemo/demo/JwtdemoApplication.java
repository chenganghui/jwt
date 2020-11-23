package com.jwtdemo.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jwtdemo.demo.mapper")
public class JwtdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtdemoApplication.class, args);
    }

}
