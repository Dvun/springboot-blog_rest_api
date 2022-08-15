package com.springboot.blog;

import com.springboot.blog.entity.Role;
import com.springboot.blog.service.auth.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootBlogRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
    }

    @Bean
    CommandLineRunner run(AuthService authService) {
        return args -> {
            if (authService.findByRole("ROLE_ADMIN") == null) {
                authService.createRole(new Role(null, "ROLE_ADMIN"));
            }
            if (authService.findByRole("ROLE_USER") == null) {
                authService.createRole(new Role(null, "ROLE_USER"));
            }
        };
    }

}
