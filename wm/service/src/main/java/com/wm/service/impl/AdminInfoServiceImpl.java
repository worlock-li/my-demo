package com.wm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.mapper.AdminInfoMapper;
import com.wm.pojo.PageResult;
import com.wm.pojo.WmAdmin;
import com.wm.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author wm
 */

@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    @Autowired
    private AdminInfoMapper mapper;

    @Override
    public List<WmAdmin> findAll() {
        return mapper.findAll();
    }

    @Override
    public void deleteAdmin(int id) {
        mapper.deleteAdmin(id);
    }

    @Override
    public void add(WmAdmin wmAdmin) {
        mapper.add(wmAdmin);
    }

    @Override
    public WmAdmin findOneById(int id) {
        return mapper.findOneById(id);
    }

    @Override
    public void update(WmAdmin wmAdmin) {
        mapper.update(wmAdmin);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        // 使用分页插件分页
        PageHelper.startPage(pageNum, pageSize);
        List<WmAdmin> list = findAll();
        Page<WmAdmin> page = (Page<WmAdmin>) list;

        // 获取当前页的数据
        List<WmAdmin> result = page.getResult();
        //获取总条数
        long total = page.getTotal();
        return new PageResult(total, result);
    }

    @Override
    public PageResult findPage(String searchInfo, int pageNum, int pageSize) {
        // 使用分页插件分页
        PageHelper.startPage(pageNum, pageSize);
        if (searchInfo != null && searchInfo.trim().length() != 0) {
            String username = "%"+searchInfo +"%";
            List<WmAdmin> wmAdmins = mapper.searchByUsername(username);
            Page<WmAdmin> page = (Page<WmAdmin>) wmAdmins;

            // 获取当前页的数据
            List<WmAdmin> result = page.getResult();
            //获取总条数
            long total = page.getTotal();
            return new PageResult(total, result);

        } else {
            return findPage(pageNum, pageSize);
        }
    }
}
