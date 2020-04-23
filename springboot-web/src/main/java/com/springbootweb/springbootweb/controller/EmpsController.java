package com.springbootweb.springbootweb.controller;

import com.springbootweb.springbootweb.dao.DepartmentDao;
import com.springbootweb.springbootweb.dao.EmployeeDao;
import com.springbootweb.springbootweb.entities.Department;
import com.springbootweb.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

/**
 * @author emps
 */
@Controller
public class EmpsController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String findAll(Map<String, Object> map) {
        Collection<Employee> list = employeeDao.getAll();
        map.put("emps", list);
        return "/emp/list";
    }

    @GetMapping("/addemp")
    public String toAddPage(Map<String, Object> map) {
        // 点击添加按钮， 跳转页面， 查询出所有的部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        map.put("depts", departments);
        return "/emp/add";
    }

    @PostMapping("/emp")
    public String add(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @PostMapping("/emp/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        // model 的作用和 map类似， 页面上可以直接获取
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "/emp/add";
    }

    @PutMapping("/emp")
    public String update(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

}
