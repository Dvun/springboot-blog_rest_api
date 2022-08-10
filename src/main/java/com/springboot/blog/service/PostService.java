package com.springboot.blog.service;


import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.UpdatePostDto;
import com.springboot.blog.utils.PostResponse;

public interface PostService {

    PostDto create(PostDto post);
    PostResponse getAll(int page, int size, String sort, String sortBy);
    PostDto getById(Long id);
    PostDto update(Long id, UpdatePostDto dto);
    void delete(Long id);
}
