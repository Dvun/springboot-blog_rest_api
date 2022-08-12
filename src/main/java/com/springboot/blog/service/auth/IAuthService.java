package com.springboot.blog.service.auth;

import com.springboot.blog.dto.user.LoginDto;
import com.springboot.blog.dto.user.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface IAuthService {

    void login(LoginDto dto);
    ResponseEntity<?> register(RegisterDto dto);

}
