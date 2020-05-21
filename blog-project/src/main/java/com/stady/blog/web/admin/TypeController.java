package com.stady.blog.web.admin;

import com.stady.blog.pojo.Type;
import com.stady.blog.service.TypeService;
import com.sun.scenario.effect.Merge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityExistsException;
import java.util.List;

/**
 * type controller
 * @author 李晓飞
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    /**
     * @PageableDefault() 里面的设置参数：
     *  size ： 每页显示的条数
     *  sort： 根据什么排序
     *  direction：指定是倒序或者正序
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String types(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC)
                                    Pageable pageable, Model model) {
        // Pageable 对象，前端传递的参数，会自动从封装到Pageable对象里面,
        // 可以使用注解的方式来进行分页配置

        /*
        *   Pageable 里面的参数：
        *   content: [{id:xx, name:xx}, {id:xx, name:xx}],
        *   "last" : false, // 表示是不是最后一页
        *   "totalPage" : 5, //总页数
        *   "totalElements" : 100, // 总条数
        *   "size" : 10, // 每页显示的条数
        *   "number" : 0, //
        *   "first" : true, // 表示是否是第一页
        *   "sort" : [{  // 排序方式
        *       "direction" : "DESC", // 倒叙/正序
        *       "property" : "id", // 根据id排序
        *       "ignoreCase" : false，
        *       "nullHandling" : "NATIVE",
        *       "ascending" : false,
        *   }],
        *   "numberOfElements" : 15
        *
        * */

        // 将数据添加到 model中， 页面可以直接获取
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String toAddPage() {
        return "admin/type-input";
    }

    @PostMapping("/types/save")
    public String save(Type type, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("tipMessage", "添加失败，数据重复");
            return "redirect:/admin/types/input";
        }
        try {
            Type tp = typeService.saveType(type);
            attributes.addFlashAttribute("message", "操作成功");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "操作失败");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/type-input";
    }

    @PostMapping("/types/edit/{id}")
    public String edit(Type type, @PathVariable Long id, RedirectAttributes attributes, Model model) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            model.addAttribute("tipMessage", "修改失败，数据重复");
            return "admin/type-input";
        }
        try {
            Type tp = typeService.updateType(id, type);
            attributes.addFlashAttribute("message", "操作成功");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "操作失败");
            e.printStackTrace();
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/del/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            typeService.deleteType(id);
            attributes.addFlashAttribute("message", "删除成功");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "删除失败");
            e.printStackTrace();
        }
        return "redirect:/admin/types";
    }

}
