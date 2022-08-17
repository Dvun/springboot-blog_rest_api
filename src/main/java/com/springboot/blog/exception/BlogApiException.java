package com.springboot.blog.exception;

public class BlogApiException extends RuntimeException {

    private final String message;

    public BlogApiException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
