package com.springbootweb.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

//    @PostMapping
//    @GetMapping
//    @DeleteMapping
//    @PutMapping
    // 分别对应 post、get、delete、put请求

    @PostMapping("/user/login")
    public String login(String username, String password, Map<String, Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && "123".equals(password)) {
            // 登陆成功， 为了防止刷新页面，表单重复提交，这里使用重定向
            // main.html 在MyMvcConfig中进行了配置
            // registry.addViewController("/main.html").setViewName("dashboard"); 还是跳转到dashboard
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
            //return "dashboard";
        } else {
            map.put("msg", "登录失败");
            return "login";
        }
    }
}
