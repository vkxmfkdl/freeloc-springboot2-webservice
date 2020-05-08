package com.jojoldu.book.springboot.web.Dto;

import com.jojoldu.book.springboot.domain.reply.Replys;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class ReplysListResponseDto {
    private Long id;
    private Long pageNum;
    private String content;
    private String author;
    private String modifiedDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ReplysListResponseDto(Replys entity) {
        this.id = entity.getId();
        this.pageNum = entity.getPageNum();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate().format(this.formatter);
    }
}
