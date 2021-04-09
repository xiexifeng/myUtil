package com.xifeng.util;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xifeng.util.dao")
public class UitlApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(UitlApplication.class, args);
    }
}
