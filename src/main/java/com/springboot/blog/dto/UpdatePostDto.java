package com.springboot.blog.dto;


import lombok.Data;

@Data
public class UpdatePostDto {

    private String title;
    private String description;
    private String content;

}
