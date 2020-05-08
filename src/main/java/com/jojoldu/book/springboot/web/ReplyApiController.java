package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.ReplysService;
import com.jojoldu.book.springboot.web.Dto.ReplysSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//1. 댓글 등록 추가
@RequiredArgsConstructor
@RestController
public class ReplyApiController {
    private final ReplysService replysService;

    @PostMapping("/api/v1/replys")
    public Long save(@RequestBody ReplysSaveRequestDto requestDto){
        return replysService.save(requestDto);
    }
}
