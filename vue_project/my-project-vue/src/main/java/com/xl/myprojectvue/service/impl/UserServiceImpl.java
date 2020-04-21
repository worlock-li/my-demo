package com.xl.myprojectvue.service.impl;

import com.xl.myprojectvue.mapper.UserMapper;
import com.xl.myprojectvue.pojo.User;
import com.xl.myprojectvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
