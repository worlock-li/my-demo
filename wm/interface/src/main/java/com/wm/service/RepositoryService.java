package com.wm.service;

import com.wm.pojo.PageResult;
import com.wm.pojo.Repository;

import java.util.List;

/**
 * @author wh
 */
public interface RepositoryService {

    List<Repository> findAll();

    PageResult findPage(String name, int pageNum, int pageSize);

    PageResult findPage(int pageNum, int pageSize);

    Repository findOneById(int id);

    void delete(int id);

    void add(String address, int capacity, int admin_id, String status);

    void update(String address, int capacity, int admin_id, String status, int id);
}
