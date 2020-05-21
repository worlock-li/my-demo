package com.stady.blog.web.admin;

import com.stady.blog.pojo.Tag;
import com.stady.blog.service.TagService;
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

@Controller
@RequestMapping("/admin")
public class TagsController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC)
                                Pageable pageable, Model model) {
        // 分页查询
        model.addAttribute("page", tagService.listTags(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String toAddPage() {
        return "admin/tag-input";
    }

    @PostMapping("/tags/save")
    public String save(Tag tag, RedirectAttributes attributes) {
        Tag tag1 = tagService.getByName(tag.getName());
        if (tag1 != null) {
            attributes.addFlashAttribute("tipMessage", "添加失败，数据重复");
            return "redirect:/admin/tags/input";
        }
        try {
            Tag tg = tagService.saveTag(tag);
            attributes.addFlashAttribute("message", "操作成功");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "操作失败");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tag-input";
    }

    @PostMapping("/tags/edit/{id}")
    public String edit(Tag tag, @PathVariable Long id, RedirectAttributes attributes, Model model) {
        Tag tag1 = tagService.getByName(tag.getName());
        if (tag1 != null) {
            model.addAttribute("tipMessage", "修改失败，数据重复");
            return "admin/tag-input";
        }
        try {
            Tag tg = tagService.updateTag(id, tag);
            attributes.addFlashAttribute("message", "操作成功");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "操作失败");
            e.printStackTrace();
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/del/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            tagService.deleteTag(id);
            attributes.addFlashAttribute("message", "删除成功");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "删除失败");
            e.printStackTrace();
        }
        return "redirect:/admin/tags";
    }

}
