package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.Dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.Dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.Dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.Dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        //update쿼리를 따로 안날려도된다. JPA의 영속성이용, 트랜잭션 끝날 때 해당 테이블에 변경분을 반영한다.
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 게시물이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // posts-> new PostsListResponseDto(posts)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new
                        IllegalArgumentException("해당 게시물이 없습니다. id="+id));

        postsRepository.deleteById(id);
    }

    public HashMap<String,Boolean> isAccessPossible(ArrayList<String> info){
        HashMap<String,Boolean> accessFlag = new HashMap<>();
        if (info.get(0).equals(info.get(1))) accessFlag.put("flag",true);
        else accessFlag.put("flag",false);
        return accessFlag;
    }

}
