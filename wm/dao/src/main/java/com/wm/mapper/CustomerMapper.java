package com.wm.mapper;

import com.wm.pojo.Customer;
import com.wm.pojo.Supplier;

import java.util.List;

public interface CustomerMapper {

    List<Customer> findAll();

    List<Supplier> searchByName(String name);

    void delete(int id);

    Customer findOneById(int id);

    void add(Customer customer);

    void update(Customer customer);
}
