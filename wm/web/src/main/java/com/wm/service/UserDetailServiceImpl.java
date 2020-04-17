package com.wm.service;

import com.wm.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailServiceImpl implements UserDetailsService {

    //在 Spring security 里面不能用到 dubbo 里面的内容，需要在配置文件中注入
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //list 集合 一个用户可以拥有多个权限
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_USER"));
        User user = userService.findOne(username);

        if (user == null) {
            return null;
        } else {
            // User user = new User(username, "123", list);
            org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.User(username, user.getPassword(), list);
            return user1;
        }
    }
}
