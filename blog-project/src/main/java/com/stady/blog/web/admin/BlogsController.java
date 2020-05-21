package com.stady.blog.web.admin;

import com.stady.blog.pojo.Blog;
import com.stady.blog.pojo.BlogQuery;
import com.stady.blog.pojo.User;
import com.stady.blog.service.BlogService;
import com.stady.blog.service.TagService;
import com.stady.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author blogs controller
 */

@Controller
@RequestMapping("/admin")
public class BlogsController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_BLOGS = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 跳转到blogs页面
     * @return
     */
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC)
                                    Pageable pageable, Model model, BlogQuery blog) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlogs(pageable, blog));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC)
                                Pageable pageable, Model model, BlogQuery blog) {
        model.addAttribute("page", blogService.listBlogs(pageable, blog));
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("blog", new Blog());
        // 获取分类列表和标签列表
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTags());
        return INPUT;
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("blog", new Blog());
        // 获取分类列表和标签列表
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTags());
        Blog blog = blogService.getById(id);
        blog.init();
        model.addAttribute("blog", blog);
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTags(blog.getTagIds()));
        try {
            blogService.save(blog);
            attributes.addFlashAttribute("message", "操作成功");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "操作失败");
        }
        return REDIRECT_BLOGS;
    }

    @GetMapping("/blogs/{id}/del")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            blogService.delete(id);
            attributes.addFlashAttribute("message", "删除成功");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "删除失败");
            e.printStackTrace();
        }
        return REDIRECT_BLOGS;

    }

}
