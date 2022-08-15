package com.springboot.blog.service.post;


import com.springboot.blog.dto.post.PostDto;
import com.springboot.blog.dto.post.UpdatePostDto;
import com.springboot.blog.utils.post.PostResponse;

public interface PostService {

    PostDto create(PostDto post);
    PostResponse getAll(int page, int size, String sort, String sortBy);
    PostDto getById(Long id);
    PostDto update(Long id, UpdatePostDto dto);
    void delete(Long id);
}
