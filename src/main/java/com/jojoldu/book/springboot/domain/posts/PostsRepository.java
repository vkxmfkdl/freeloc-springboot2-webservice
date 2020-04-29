package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Entity클래스와 Repo클래스는 같이 위치해있어야한다.
public interface PostsRepository extends JpaRepository<Posts,Long> { //JpaRepository를 상속받으면 기본적인CRUD메소드 사용가능

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
