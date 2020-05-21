package com.stady.blog.web;

import com.stady.blog.pojo.BlogQuery;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author index controller
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model) {
        model.addAttribute("page", blogService.listBlogs(pageable));
        // 页面展示type， 显示条数为6
        model.addAttribute("types", typeService.listTypeTop(6));
        // 页面展示tag， 显示条数为10
        model.addAttribute("tags", tagService.listTagTop(10));
        // 页面展示blog推荐， 显示条数为6
        model.addAttribute("recommendBlog", blogService.listRecommendBlogTop(6));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String toBlogPage(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getOneAndConvert(id));
        return "blog";
    }

    @GetMapping("/about")
    public String toAboutPage() {
        return "about";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                 @RequestParam String query, Pageable pageable, Model model) {
        model.addAttribute("query", query);
        model.addAttribute("page", blogService.listBlogsQuery("%"+query+"%", pageable));
        return "/search";
    }

    @GetMapping("/footer/newblog")
    public String newBlogs(Model model) {
        model.addAttribute("newBlogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newBlogList";
    }


}

/*
        实体类
    *   博客类 blog
    *   分类 type
    *   标签 tag
    *   评论 comment
    *   用户 user
    *
    *   blog --> type    多对一
        blog --> tag     多对多
        blog --> user    多对一
        blog --> comment 一对多

        parentComment --> replayComment  一对多
        一个回复下面可能有多个子回复， 在一个实体类中进行关联

        blog 属性：
        分类、 标签、 评论、 用户
        标题， 内容， 首图， 标记（原创/转载/翻译）， 浏览次数， 赞赏开启， 版权开启， 评论开启， 发布， 推荐，  创建时间， 更新时间

        comment 属性：
        昵称， 邮箱， 头像， 评论内容， 创建时间

        user 属性：
        昵称， 用户名（后台登陆）， 密码， 邮箱， 类型， 头像， 创建时间， 更新时间
    * */
