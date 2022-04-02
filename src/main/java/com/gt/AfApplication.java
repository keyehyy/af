package com.gt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gt.**.mapper")
public class AfApplication {

    public static void main(String[] args) {
        System.out.println("============================================>>>>>项目开始启动>>>>============================================");
        long start = System.currentTimeMillis();
        SpringApplication.run(AfApplication.class, args);
        long time = (System.currentTimeMillis() - start)/1000;
        System.out.println("============================================>>>>>>>启动成功>>>>>>============================================");
        System.out.println("============================================>>>>>>>耗时"+time+"秒>>>>>>=============================================");
    }

}
