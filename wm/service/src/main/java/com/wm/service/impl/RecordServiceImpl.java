package com.wm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.mapper.RecordMapper;
import com.wm.pojo.*;
import com.wm.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author wh
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper mapper;

    @Override
    public void wareHousing(Record record) {
        mapper.wareHousing(record);
    }

    @Override
    public List<Record> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageResult findPage(Record record, int pageNum, int pageSize) {
        // 使用分页插件分页
        PageHelper.startPage(pageNum, pageSize);
        List<Record> records = null;

        if (record != null) {
            if (record.getRepository_id() != 0) {
                if (record.getChange_time() != null) {
                    records = mapper.search(record.getRepository_id(), record.getChange_time());
                } else {
                    records = mapper.searchByReId(record.getRepository_id());
                }
            } else {
                if (record.getChange_time() != null) {
                    records = mapper.searchByTime(record.getChange_time());
                } else {
                    records = mapper.findAll();
                }

            }
        } else {
            records = mapper.findAll();
        }
        Page<Record> page = (Page<Record>) records;
        // 获取当前页的数据
        List<Record> result = page.getResult();

        //获取总条数
        long total = page.getTotal();
        return new PageResult(total, result);

    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        // 使用分页插件分页
        PageHelper.startPage(pageNum, pageSize);
        List<Record> list = findAll();
        Page<Record> page = (Page<Record>) list;

        // 获取当前页的数据
        List<Record> result = page.getResult();
        //获取总条数
        long total = page.getTotal();
        return new PageResult(total, result);
    }

    @Override
    public List<Record> findRecord() {
        return mapper.findRecord();
    }

    @Override
    public void outStock(Record record) {
        mapper.outStock(record);
    }

}
