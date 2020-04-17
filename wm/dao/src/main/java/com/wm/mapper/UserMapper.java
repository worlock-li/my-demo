package com.wm.mapper;

import com.wm.pojo.User;

public interface UserMapper {

    User findUserByNameAndPassword(String username, String password);

    User findOne(String username);

    void changePassword(int id, String password);
}
