package com.stady.blog.web.admin;

import com.stady.blog.pojo.User;
import com.stady.blog.service.UserService;
import com.stady.blog.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

/**
 * @author LoginController
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String toLoginPage() {
        return "/admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, Md5Utils.md5(password));
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            // 直接重定向到配置类 WebConfig 里面设置的 main.html 里面
            return "redirect:/admin/main.html";
        } else {
            attributes.addFlashAttribute("message", "用户名或者密码错误");
            // 使用redirect 重定向， 要使用RedirectAttributes来记录数据
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
