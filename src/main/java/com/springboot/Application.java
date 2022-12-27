package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 스프링 부트의 자동설정, 스프링 빈 읽기와 생성을 모두 자동으로 설정한다.
// 이 Annotation이 있는 위치부터 설정을 읽기 떄문에 프로젝트의 최상단에 위치해야 한다.
@EnableJpaAuditing // Jpa Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
