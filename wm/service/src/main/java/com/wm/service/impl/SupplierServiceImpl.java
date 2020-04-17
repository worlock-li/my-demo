package com.wm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.mapper.SupplierMapper;
import com.wm.pojo.PageResult;
import com.wm.pojo.Supplier;
import com.wm.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wh
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper mapper;
    @Override
    public List<Supplier> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageResult findPage(String searchInfo, int pageNum, int pageSize) {
        // 使用分页插件分页
        PageHelper.startPage(pageNum, pageSize);
        if (searchInfo != null && searchInfo.trim().length() != 0) {
            String name = "%"+searchInfo +"%";

            System.out.println(name);
            List<Supplier> suppliers = mapper.searchByName(name);

            Page<Supplier> page = (Page<Supplier>) suppliers;

            // 获取当前页的数据
            List<Supplier> result = page.getResult();
            //获取总条数
            long total = page.getTotal();
            return new PageResult(total, result);
        } else {
            return findPage(pageNum, pageSize);
        }
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        // 使用分页插件分页
        PageHelper.startPage(pageNum, pageSize);
        List<Supplier> list = findAll();
        Page<Supplier> page = (Page<Supplier>) list;

        // 获取当前页的数据
        List<Supplier> result = page.getResult();
        //获取总条数
        long total = page.getTotal();
        return new PageResult(total, result);
    }

    @Override
    public void delete(int id) {
        mapper.delete(id);
    }

    @Override
    public Supplier findOneById(int id) {
        return mapper.findOneById(id);
    }

    @Override
    public void add(Supplier supplier) {
        mapper.add(supplier);
    }

    @Override
    public void update(Supplier supplier) {
        mapper.update(supplier);
    }
}
