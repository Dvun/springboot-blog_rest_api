package com.springboot.blog.service;


import com.springboot.blog.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto create(PostDto post);
    List<PostDto> getAll();
}
