package com.springboot.blog.service.user;

import com.springboot.blog.entity.User;

public interface UserService {

    User getUser(String email);

}
