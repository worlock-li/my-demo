package com.stady.blog.web;

import com.stady.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchviesShowController {

    @Autowired
    private BlogService BlogService;

    @GetMapping("/archvies")
    public String archvies(Model model) {
        model.addAttribute("archvieMap", BlogService.archvieBlog());
        model.addAttribute("BlogCount", BlogService.countBlog());
        return "archvies";
    }
}
