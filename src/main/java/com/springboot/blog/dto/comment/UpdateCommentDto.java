package com.springboot.blog.dto.comment;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateCommentDto {

    @NotEmpty(message = "Name is required!")
    private String name;

    @NotEmpty(message = "Email is required!")
    @Email(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Email must be a well-formed email address!")
    private String email;

    @NotEmpty(message = "Message is required!")
    @Size(min = 10, message = "Comment message should have at least 10 characters!")
    private String message;


    public void setEmail(String email) {
        this.email = email.toLowerCase().trim();
    }

    public void setName(String name) {
        this.name = name.trim();
    }
}
