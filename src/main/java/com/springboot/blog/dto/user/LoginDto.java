package com.springboot.blog.dto.user;

import lombok.Data;

import java.util.regex.Pattern;

@Data
public class LoginDto {

    private String usernameOrEmail;
    private String password;

    public void setUsernameOrEmail(String usernameOrEmail) {
        String emailRegex = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        if (Pattern.compile(emailRegex).matcher(usernameOrEmail).matches())
            this.usernameOrEmail = usernameOrEmail.toLowerCase();
        else
            this.usernameOrEmail = usernameOrEmail;
    }
}
