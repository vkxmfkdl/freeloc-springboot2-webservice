/*
1.역할 : 메인 클래스
 */

package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication //스프링 Bean읽기와 생성을 모두 자동 설정, 프로젝트 최상단에 정의
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args); //내장 WAS를 실행
    }
}
