package com.springboot.blog.controller;


import com.springboot.blog.dto.user.LoginDto;
import com.springboot.blog.dto.user.RegisterDto;
import com.springboot.blog.service.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto dto) {
        authService.login(dto);
        return new ResponseEntity<>("Logged successfully!", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {
        return authService.register(dto);
    }
}
