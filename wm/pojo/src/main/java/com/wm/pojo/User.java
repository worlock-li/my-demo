package com.wm.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String username ;
    private String password ;
    private Integer user_function_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUser_function_id() {
        return user_function_id;
    }

    public void setUser_function_id(Integer user_function_id) {
        this.user_function_id = user_function_id;
    }
}
