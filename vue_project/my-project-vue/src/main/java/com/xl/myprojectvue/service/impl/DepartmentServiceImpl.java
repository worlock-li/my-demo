package com.xl.myprojectvue.service.impl;

import com.xl.myprojectvue.mapper.DepartmentMapper;
import com.xl.myprojectvue.pojo.Department;
import com.xl.myprojectvue.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DepartmentServiceImpl
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper mapper;

    @Override
    public List<Department> findAll() {
        return mapper.findAll();
    }

    @Override
    public void add(Department department) {
        mapper.add(department);
    }

    @Override
    public void delete(int id) {
        mapper.delete(id);
    }

    @Override
    public Department getById(Integer id) {
        return mapper.getById(id);
    }

    @Override
    public void update(Department department) {
        mapper.update(department);
    }

    @Override
    public List<Department> searchByName(String searchName) {
        String name = "%" + searchName + "%";
        return mapper.searchByName(name);
    }
}
