package com.jake.springboot.service.posts;

import com.jake.springboot.domain.posts.Posts;
import com.jake.springboot.domain.posts.PostsRepository;
import com.jake.springboot.dto.PostsResponseDto;
import com.jake.springboot.dto.PostsSaveRequestDto;
import com.jake.springboot.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no posts. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no posts. id=" + id));
        return new PostsResponseDto(entity);
    }
}
