package com.stady.blog.web;

import com.stady.blog.pojo.Comment;
import com.stady.blog.pojo.User;
import com.stady.blog.service.BlogService;
import com.stady.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;

/**
 * @author lxf
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentsByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        // 前端传递的参数有 blogId, parentCommentId, content， email， nickName
        // 获取到comment里面blogId
        Long blogId = comment.getBlog().getId();
        // 通过获取blog对象， 将blog对象set到comment对象中
        comment.setBlog(blogService.getById(blogId));
        // 用户评论的头像是固定的一张， 在properties文件中指定图片
        // 通过spring的注解 @Value 来进行获取
        comment.setAvatar(avatar);
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
            comment.setNickName(user.getNickName());
        } else {
            comment.setAdminComment(false);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }

}
