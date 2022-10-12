package com.sparta.hanghae_homework_week04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HanghaeHomeworkWeek04Application {

    public static void main(String[] args) {
        SpringApplication.run(HanghaeHomeworkWeek04Application.class, args);
    }

}
