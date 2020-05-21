package com.stady.blog.web;

import com.stady.blog.pojo.BlogQuery;
import com.stady.blog.pojo.Type;
import com.stady.blog.service.BlogService;
import com.stady.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author l
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable, Model model, @PathVariable Long id) {
        List<Type> types = typeService.listTypeTop(1000);
        // 第一次进入页面， 没有选择分类
        if (id == -1) {
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlogs(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
