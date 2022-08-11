package com.springboot.blog.dto.post;

import com.springboot.blog.dto.comment.CommentDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class PostDto {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters!")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters!")
    private String description;

    @NotEmpty
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<CommentDto> comments;
}
