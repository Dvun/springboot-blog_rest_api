package com.springboot.blog.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {

    private String token;
    private String username;
    private String email;

    public JwtResponse(String token, String username, String email) {
        this.token = token;
        this.username = username;
        this.email = email;
    }
}
