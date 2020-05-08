package com.jojoldu.book.springboot.web.Dto;

import com.jojoldu.book.springboot.domain.reply.Replys;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplysSaveRequestDto {
    private String author;
    private String content;
    private Long pageNum;

    @Builder
    public ReplysSaveRequestDto(String author, String content, Long pageNum) {
        this.author = author;
        this.content = content;
        this.pageNum = pageNum;
    }

    public Replys toEntity(){
        return Replys.builder()
                .author(author)
                .content(content)
                .pageNum(pageNum)
                .build();
    }
}
