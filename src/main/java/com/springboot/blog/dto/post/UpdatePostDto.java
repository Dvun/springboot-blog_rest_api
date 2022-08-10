package com.springboot.blog.dto.post;


import lombok.Data;

@Data
public class UpdatePostDto {

    private String title;
    private String description;
    private String content;

}
