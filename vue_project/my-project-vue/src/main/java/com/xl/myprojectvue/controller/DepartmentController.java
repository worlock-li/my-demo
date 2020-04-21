package com.xl.myprojectvue.controller;

import com.xl.myprojectvue.pojo.Department;
import com.xl.myprojectvue.pojo.Result;
import com.xl.myprojectvue.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DepartmentController
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    /**
     *  使用set方式注入， 直接使用变量方式注入是不严谨的
     */
    private DepartmentService departmentService;
    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     *  指定为get请求，安全性考虑
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody Department department) {
        try {
            departmentService.add(department);
            return new Result(200, "添加成功", true);
        } catch (Exception e) {
            return new Result(400, "添加失败", false);
        }
    }

    @RequestMapping(value = "/deletedById/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable Integer id) {
        try {
            departmentService.delete(id);
            return new Result(200, "删除成功", true);
        } catch (Exception e) {
            return new Result(400, "删除失败", false);
        }
    }

    @RequestMapping(value = "/getById/{id}",method = RequestMethod.GET)
    public Department getById(@PathVariable Integer id) {
        return departmentService.getById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody Department department) {
        try {
            departmentService.update(department);
            return new Result(200, "更新成功", true);
        } catch (Exception e) {
            return new Result(400, "更新失败", false);
        }
    }

    @RequestMapping(value = "/searchByName/{searchName}", method = RequestMethod.GET)
    public List<Department> searchByName(@PathVariable String searchName) {
        System.out.println(searchName+ "----");
        return departmentService.searchByName(searchName);
    }
}
