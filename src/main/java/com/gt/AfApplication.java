package com.gt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@MapperScan("com.gt.**.mapper")
public class AfApplication {

    public static void main(String[] args) {
        SpringApplication.run(AfApplication.class, args);
        System.out.println("-------------------------------------------------------启动成功------------------------------------------------------------------------");
    }

}
