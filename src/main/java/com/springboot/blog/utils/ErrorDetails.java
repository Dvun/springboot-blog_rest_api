package com.springboot.blog.utils;

import java.util.Date;

public class ErrorDetails {

    private final Date timestamp;
    private final String message;
    private final String path;

    public ErrorDetails(Date timestamp, String message, String path) {
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
