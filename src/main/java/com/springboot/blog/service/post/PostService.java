package com.springboot.blog.service.post;


import com.springboot.blog.dto.post.CreatePostDto;
import com.springboot.blog.dto.post.PostDto;
import com.springboot.blog.dto.post.UpdatePostDto;
import com.springboot.blog.dto.post.PostResponseDto;
import org.springframework.http.ResponseEntity;

public interface PostService {

    ResponseEntity<PostDto> create(CreatePostDto dto);
    PostResponseDto getAll(int page, int size, String sort, String sortBy);
    PostDto getById(Long id);
    PostDto update(Long id, UpdatePostDto dto);
    void delete(Long id);
}
