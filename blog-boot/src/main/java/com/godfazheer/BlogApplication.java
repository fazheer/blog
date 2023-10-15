package com.godfazheer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.godfazheer.dao")
public class BlogApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BlogApplication.class, args);

    }

}
