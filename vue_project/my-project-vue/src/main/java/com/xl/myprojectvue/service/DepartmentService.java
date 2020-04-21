package com.xl.myprojectvue.service;

import com.xl.myprojectvue.pojo.Department;

import java.util.List;

/**
 * @author DepartmentService
 */
public interface DepartmentService {

    /**
     *  findALL Department
     * @return
     */
    List<Department> findAll();

    /**
     * add department
     * @param department
     */
    void add(Department department);

    /**
     * delete by id
     * @param id
     */
    void delete(int id);

    Department getById(Integer id);

    void update(Department department);

    List<Department> searchByName(String searchName);
}
