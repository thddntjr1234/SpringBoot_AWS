package com.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // HelloTest에서 JPA가 요구하는 엔티티를 가지지 않기 때문에 어노테이션을 분리해준다.
public class JpaConfig {
}
