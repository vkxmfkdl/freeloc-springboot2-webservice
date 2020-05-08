package com.jojoldu.book.springboot.domain.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplysRepository extends JpaRepository<Replys,Long> {
    List<Replys> findAllByPageNum(Long pageNum);
}
