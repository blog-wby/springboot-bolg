package com.wbyweb.bolg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan(value = "com.wbyweb.bolg.mapper")
@SpringBootApplication
@EnableCaching
public class SpringbootBolgApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBolgApplication.class, args);
    }
}
