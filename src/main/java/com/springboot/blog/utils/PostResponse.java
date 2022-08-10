package com.springboot.blog.utils;

import com.springboot.blog.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostResponse {

    private List<PostDto> content;
    private int page;
    private int size;
    private Long totalElements;
    private int totalPages;
    private boolean last;

}

