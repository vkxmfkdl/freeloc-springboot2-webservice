package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.domain.reply.Replys;
import com.jojoldu.book.springboot.domain.reply.ReplysRepository;
import com.jojoldu.book.springboot.web.Dto.ReplysListResponseDto;
import com.jojoldu.book.springboot.web.Dto.ReplysSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplysService {
    private final ReplysRepository replysRepository;

    @Transactional
    public Long save(ReplysSaveRequestDto requestDto){ return replysRepository.save(requestDto.toEntity()).getId();}

    public List<ReplysListResponseDto> findAll(Long pageNum){
        return replysRepository.findAllByPageNum(pageNum).stream()
                .map(ReplysListResponseDto::new)
                .collect(Collectors.toList());
    }

    private Sort sortByIdDesc(){
        return new Sort(Sort.Direction.DESC,"id");
    }

}
