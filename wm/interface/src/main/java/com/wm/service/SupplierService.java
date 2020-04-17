package com.wm.service;

import com.wm.pojo.PageResult;
import com.wm.pojo.Supplier;

import java.util.List;

/**
 * @author wh
 */
public interface SupplierService {

    List<Supplier> findAll();

    PageResult findPage(String searchInfo, int pageNum, int pageSize);

    PageResult findPage(int pageNum, int pageSize);

    void delete(int id);

    Supplier findOneById(int id);

    void add(Supplier supplier);

    void update(Supplier supplier);
}
