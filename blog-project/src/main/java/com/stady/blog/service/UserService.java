package com.stady.blog.service;

import com.stady.blog.pojo.User;

/**
 * @author UserService
 */
public interface UserService {

    User checkUser(String username, String password);
}
