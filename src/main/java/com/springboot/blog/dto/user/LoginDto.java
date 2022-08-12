package com.springboot.blog.dto.user;

import lombok.Data;

@Data
public class LoginDto {

    private String usernameOrEmail;
    private String password;

}
