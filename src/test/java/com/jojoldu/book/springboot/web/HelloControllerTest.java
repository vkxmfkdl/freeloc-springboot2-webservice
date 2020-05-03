package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //Junit에 내장된 실행자 외에 다른 클래스(SpringRunner)를 실행
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc; // 웹 API를 테스트할때 사용

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // HTTP Header의 Status를 검증
                .andExpect(content().string(hello)); // 응답본문의 내용을 검증
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name ="hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                .param("name",name) // API테스트할 때 사용될 요청 파라미터 설정, String만 가능
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name))) //#json응답값을 필드별로 검증, $를 기준으로 필드명 명시
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}
