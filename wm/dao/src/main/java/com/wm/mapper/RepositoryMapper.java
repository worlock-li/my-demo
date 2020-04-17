package com.wm.mapper;

import com.wm.pojo.Customer;
import com.wm.pojo.Repository;
import com.wm.pojo.Supplier;

import java.util.List;

/**
 * @author wh
 */
public interface RepositoryMapper {

    List<Repository> findAll();

    List<Repository> searchByAddress(String address);

    void delete(int id);

    Repository findOneById(int id);

    void add(String address, int capacity, int admin_id, String status);

    void update(String address, int capacity, int admin_id, String status, int id);
}
