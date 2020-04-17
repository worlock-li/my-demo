package com.wm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.mapper.RepositoryMapper;
import com.wm.pojo.PageResult;
import com.wm.pojo.Repository;
import com.wm.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wh
 */
@Service
public class RepositoryServiceImpl implements RepositoryService {
    @Autowired
    private RepositoryMapper mapper;

    @Override
    public List<Repository> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageResult findPage(String searchInfo, int pageNum, int pageSize) {
        // 使用分页插件分页
        PageHelper.startPage(pageNum, pageSize);
        if (searchInfo != null && searchInfo.trim().length() != 0) {
            String address = "%"+searchInfo +"%";

            System.out.println(address);
            List<Repository> repositoryes = mapper.searchByAddress(address);

            Page<Repository> page = (Page<Repository>) repositoryes;

            // 获取当前页的数据
            List<Repository> result = page.getResult();
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
        List<Repository> list = findAll();
        Page<Repository> page = (Page<Repository>) list;

        // 获取当前页的数据
        List<Repository> result = page.getResult();
        //获取总条数
        long total = page.getTotal();
        return new PageResult(total, result);
    }

    @Override
    public Repository findOneById(int id) {
        return mapper.findOneById(id);
    }

    @Override
    public void delete(int id) {
        mapper.delete(id);
    }

    @Override
    public void add(String address, int capacity, int admin_id, String status) {
        mapper.add(address, capacity, admin_id, status);
    }

    @Override
    public void update(String address, int capacity, int admin_id, String status, int id) {
        mapper.update(address, capacity, admin_id, status, id);
    }
}
