package com.jojoldu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // <- 어노테이션를 사용할 경우 H2데이터베이스를 자동으로 실행
public class PostRepositoryTest {

    @Autowired
    PostsRepository postRepository;

    @After //Junit에서 단위테스트가 끝날 때마다 수행되는 메소드를 지정
    public void cleanup(){
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        String title = "테스트게시글";
        String content = "테스트본문";
        
        // 테이블 posts에 insert/update쿼리문을 실행한다. id값이 있다면 update, 없다면 insert쿼리 실행
        postRepository.save(Posts.builder()
                            .title(title)
                            .content(content)
                            .author("jojoldu@gmail.com")
                            .build());
        
        List<Posts> postsList = postRepository.findAll(); // 모든 데이터를 조회해오는 메소드

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        
    }
}
