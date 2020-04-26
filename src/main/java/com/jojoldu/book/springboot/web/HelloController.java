package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.web.Dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //JSON으로 반환하는 컨트롤러를 만들어 줍니다.
public class HelloController {


    @GetMapping("/hello") //GET요청을 받을 수 있는 API
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto") //GET요청을 받을 수 있는 API
    public HelloResponseDto hello(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
