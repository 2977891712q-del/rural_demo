package org.example.rural_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 扫描mapper包，路径对应你的mapper文件夹
@MapperScan("org.example.rural_demo.mapper")
public class RuralDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuralDemoApplication.class, args);
    }

}