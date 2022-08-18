package com.springboot.blog.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String name;
    private String lastname;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Set<Role> roles;


    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList()
        );
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
