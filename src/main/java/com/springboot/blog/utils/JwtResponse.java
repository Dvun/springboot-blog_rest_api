package com.springboot.blog.utils;

import com.springboot.blog.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JwtResponse implements Serializable {

    private UserDto user;
    private String token;

    public JwtResponse(String token, UserDto user) {
        this.token = token;
        this.user = user;
    }
}
