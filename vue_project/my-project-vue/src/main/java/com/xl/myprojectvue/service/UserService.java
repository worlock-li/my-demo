package com.xl.myprojectvue.service;

import com.xl.myprojectvue.pojo.User;

import java.util.List;

/**
 * @author UserService
 */
public interface UserService {
    /**
     * find user by user id
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * find all user
     * @return
     */
    List<User> findAll();
}
