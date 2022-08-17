package com.springboot.blog.utils;

import com.springboot.blog.dto.user.UserDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {

    private UserDto user;
    private String token;

    public JwtResponse(String token, UserDto user) {
        this.token = token;
        this.user = user;
    }
}
