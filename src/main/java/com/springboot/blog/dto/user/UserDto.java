package com.springboot.blog.dto.user;

import com.springboot.blog.entity.Role;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String username;
    private Set<String> roles;


    public void setRoles(Set<Role> roles) {
        this.roles = roles.stream().map(Role::getRole).collect(Collectors.toSet());
    }
}