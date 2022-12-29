package com.springboot.service;

import com.springboot.domain.posts.Posts;
import com.springboot.domain.posts.PostsRepository;
import com.springboot.web.dto.PostsListResponseDto;
import com.springboot.web.dto.PostsResponseDto;
import com.springboot.web.dto.PostsSaveRequestDto;
import com.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional // @Transactinal이 붙은 메서드는 메서드가 포함하고 있는 작업 중 하나라도 실패하면 전체 작업을 취소한다
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되 조회기능만 남겨서 조회속도 개선 효과를 얻을 수 있다.
    public List<PostsListResponseDto> findAllDesc() {
        // List<Posts> 리스트를 PostsListResponseDto 타입으로 바꾼다.
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // == posts -> new PostsListResponseDto(posts)
                .collect(Collectors.toList());
    }

}
