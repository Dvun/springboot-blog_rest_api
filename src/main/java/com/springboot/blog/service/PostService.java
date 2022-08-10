package com.springboot.blog.service;


import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.UpdatePostDto;

import java.util.List;

public interface PostService {

    PostDto create(PostDto post);
    List<PostDto> getAll(int page, int size);
    PostDto getById(Long id);
    PostDto update(Long id, UpdatePostDto dto);
    void delete(Long id);
}
