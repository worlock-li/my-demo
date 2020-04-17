package com.wm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.mapper.CustomerMapper;
import com.wm.pojo.Customer;
import com.wm.pojo.PageResult;
import com.wm.pojo.Supplier;
import com.wm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wh
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper mapper;
    @Override
    public List<Customer> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        // 使用分页插件分页
        PageHelper.startPage(pageNum, pageSize);
        List<Customer> list = findAll();
        Page<Customer> page = (Page<Customer>) list;

        // 获取当前页的数据
        List<Customer> result = page.getResult();
        //获取总条数
        long total = page.getTotal();
        return new PageResult(total, result);
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
    public void delete(int id) {
        mapper.delete(id);
    }

    @Override
    public Customer findOneById(int id) {
        return mapper.findOneById(id);
    }

    @Override
    public void add(Customer customer) {
        mapper.add(customer);
    }

    @Override
    public void update(Customer customer) {
        mapper.update(customer);
    }
}
