package com.xl.myprojectvue.mapper;

import com.xl.myprojectvue.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DepartmentMapper
 */
@Repository
@Mapper
public interface DepartmentMapper {

    /***
     * findAll mapper
     * @return
     */
    List<Department> findAll();

    void add(Department department);

    void delete(int id);

    Department getById(Integer id);

    void update(Department department);

    List<Department> searchByName(String name);
}
