package com.springboot.blog.service.auth;

import com.springboot.blog.dto.user.LoginDto;
import com.springboot.blog.dto.user.RegisterDto;
import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.User;
import com.springboot.blog.repository.RoleRepository;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.security.jwt.JwtUtils;
import com.springboot.blog.security.service.UserDetailsImpl;
import com.springboot.blog.utils.JwtResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;


    @Override
    public ResponseEntity<?> login(LoginDto dto) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsernameOrEmail(), dto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtUtils.generateJwtToken(auth);
        UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
        return new ResponseEntity<>(new JwtResponse(token, principal.getUsername(), principal.getEmail()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> register(RegisterDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            return new ResponseEntity<>("Email is already registered!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            return new ResponseEntity<>("Username is already registered!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        Role role = roleRepository.findByRole("ROLE_USER").orElse(null);
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
        return new ResponseEntity<>("User registered!", HttpStatus.CREATED);
    }

    @Override
    public void createRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Optional<Role> findByRole(String roleType) {
        return roleRepository.findByRole(roleType);
    }

    @Override
    public void addRoleToUser(String email, String roleType) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found with email:" + email)
        );
        Role role = roleRepository.findByRole(roleType).orElse(null);
        user.setRoles(Collections.singleton(role));
    }

}
