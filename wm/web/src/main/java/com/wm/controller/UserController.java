package com.wm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wm.pojo.User;
import com.wm.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wm
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Reference
    private UserService service;

    @RequestMapping("/login.do")
    public User findUserByNameAndPassword(String username, String password){
        return service.findUserByNameAndPassword(username, password);
    }

    @RequestMapping("/findOne.do")
    public User findOne(String username) {
        return service.findOne(username);
    }

    @RequestMapping("/changePassword.do")
    public void changePassword(int id, String password) {
        service.changePassword(id, password);
    }
}
