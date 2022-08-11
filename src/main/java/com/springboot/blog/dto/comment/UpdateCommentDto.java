package com.springboot.blog.dto.comment;

import lombok.Data;

@Data
public class UpdateCommentDto {

    private String name;
    private String email;
    private String message;

}
