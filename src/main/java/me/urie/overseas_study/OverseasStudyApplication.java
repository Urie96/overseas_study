package me.urie.overseas_study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("me.urie.overseas_study.mapper")
@EnableCaching
public class OverseasStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(OverseasStudyApplication.class, args);
    }

}
