package com.wm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wm.mapper.UserMapper;
import com.wm.pojo.User;
import com.wm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wm
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;
    @Override
    public User findUserByNameAndPassword(String username ,String password) {
        return mapper.findUserByNameAndPassword(username, password);
    }

    @Override
    public User findOne(String username) {
        return mapper.findOne(username);
    }

    @Override
    public void changePassword(int id, String password) {
        mapper.changePassword(id, password);
    }
}
