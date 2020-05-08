package com.jojoldu.book.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.domain.reply.Replys;
import com.jojoldu.book.springboot.domain.reply.ReplysRepository;
import com.jojoldu.book.springboot.service.ReplysService;
import com.jojoldu.book.springboot.web.Dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.Dto.ReplysSaveRequestDto;
import net.bytebuddy.asm.Advice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReplyApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private ReplysRepository replysRepository;

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before //매 테스트 시작 전 실행한다.
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles="USER")
    public void Replys_등록된다() throws  Exception{
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();
        String url = "http://localhost:"+port+"/api/v1/posts";

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

        Long pageNum = all.get(0).getId();
        String reply_content = "reply_content";
        String reply_author = "author";

        ReplysSaveRequestDto saveRequestDto = ReplysSaveRequestDto.builder()
                                                .author(reply_author)
                                                .content(reply_content)
                                                .pageNum(pageNum)
                                                .build();
        url = "http://localhost:"+port+"/api/v1/replys";
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(saveRequestDto)))
                .andExpect(status().isOk());

        List<Replys> replys = replysRepository.findAllByPageNum(pageNum);
        assertThat(replys.get(0).getAuthor()).isEqualTo(reply_author);
        assertThat(replys.get(0).getContent()).isEqualTo(reply_content);
        assertThat(replys.get(0).getPageNum()).isEqualTo(pageNum);

    }
}
