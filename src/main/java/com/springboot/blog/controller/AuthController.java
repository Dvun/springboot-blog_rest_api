package com.springboot.blog.controller;


import com.springboot.blog.dto.user.LoginDto;
import com.springboot.blog.dto.user.RegisterDto;
import com.springboot.blog.service.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        return authService.login(dto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {
        return authService.register(dto);
    }

}
