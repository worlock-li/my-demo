package com.wm.mapper;

import com.wm.pojo.Supplier;

import java.util.List;

public interface SupplierMapper {

    List<Supplier> findAll();

    List<Supplier> searchByName(String name);

    void delete(int id);

    Supplier findOneById(int id);

    void add(Supplier supplier);

    void update(Supplier supplier);
}
