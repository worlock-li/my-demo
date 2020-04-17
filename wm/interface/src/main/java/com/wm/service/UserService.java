package com.wm.service;

import com.wm.pojo.User;

public interface UserService {
    /**
     * login use username and password
     * @param username
     * @param password
     * @return
     */
    User findUserByNameAndPassword(String username ,String password);

    /**
     * login use username find one user
     * @param username
     * @return
     */
    User findOne(String username);

    /**
     * use id and new password change password
     * @param id
     * @param password
     */
    void changePassword(int id, String password);
}
