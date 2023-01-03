package com.springboot;

import com.springboot.config.auth.SecurityConfig;
import com.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 테스트 실행 시 error: package org.junit does not exist 오류가 발생하면서
// junit 관련 import 오류가 발생하는데 IDEA 설정 Build - Gradle 옵션에서 InteliJ IDEA로 실행되도록 수정하면 된다.
@RunWith(SpringRunner.class) // 테스트를 진행할 떄 JUnit4에 내장된 실행자 외에 다른 실행자를 실행시킨다(SpringRunner.class가 대상, 연결자 역할)
@WebMvcTest(controllers = HelloController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
}) // web(spring mvc)중 컨트롤러에 집중할 수 있는 어노테이션, @Controller, @ControllerAdvice 등을 사용가능
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈을 주입받음
    private MockMvc mvc; // 웹API를 테스트할 떄 사용(JUnit4)

    @Test
    @WithMockUser(roles="USER")
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // isOk() == Http status가 200이 아닌가, content().string("") string()내의 내용이 맞는가
        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
    }

    @Test
    @WithMockUser(roles="USER")
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;
        // perform() - 요청, andExpect() - perform 요청의 결과 검증
        mvc.perform(get("/hello/dto")
                        .param("name", name) // get 파라미터
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // jsonPath - JSON 응답값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
