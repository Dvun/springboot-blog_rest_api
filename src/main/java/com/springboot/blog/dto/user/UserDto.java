package com.springboot.blog.dto.user;

import com.springboot.blog.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<Role> roles = new HashSet<>();

}
