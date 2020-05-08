package com.jojoldu.book.springboot.domain.reply;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //절대 Setter 메소드를 만들지 않는다.
@NoArgsConstructor //기본생성자 자동추가
@Entity
public class Replys extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pageNum;

    @Column(length = 500, nullable = false)
    private String content;

    private String author;

    @Builder
    public Replys(Long pageNum, String content, String author) {
        this.pageNum = pageNum;
        this.content = content;
        this.author = author;
    }
}
