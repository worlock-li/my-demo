package com.xl.myprojectvue.controller;

import com.xl.myprojectvue.pojo.User;
import com.xl.myprojectvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author UserController
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public User getUserById(String id) {
        // @PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值, {id}
        return userService.getUserById(id);
    }

    @RequestMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

}
