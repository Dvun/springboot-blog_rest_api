package com.springboot.blog.dto.post;

import com.springboot.blog.dto.comment.CommentDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<CommentDto> comments;
}
