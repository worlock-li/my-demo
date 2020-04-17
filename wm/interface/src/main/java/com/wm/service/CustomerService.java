package com.wm.service;

import com.wm.pojo.Customer;
import com.wm.pojo.PageResult;

import java.util.List;

/**
 * @author wh
 */
public interface CustomerService {
    List<Customer> findAll();

    PageResult findPage(int pageNum, int pageSize);

    PageResult findPage(String name, int pageNum, int pageSize);

    void delete(int id);

    Customer findOneById(int id);

    void add(Customer customer);

    void update(Customer customer);
}
