package com.springboot.blog.controller;


import com.springboot.blog.dto.auth.LoginDto;
import com.springboot.blog.dto.auth.LoginResponseDto;
import com.springboot.blog.dto.user.RegisterDto;
import com.springboot.blog.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Tag(name = "Auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Login User that give a user info and access token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponseDto.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Bad Credentials!",
                    content = @Content)
    })

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto dto) {
        return authService.login(dto);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto dto) {
        return authService.register(dto);
    }

}
