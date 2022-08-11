package com.springboot.blog.utils;

import java.util.Date;
import java.util.Map;

public class ValidationErrors {

    private final Date timestamp;
    private final Map<String, String> errors;
    private final String path;

    public ValidationErrors(Date timestamp, Map<String, String> errors, String path) {
        this.timestamp = timestamp;
        this.errors = errors;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getPath() {
        return path;
    }
}
