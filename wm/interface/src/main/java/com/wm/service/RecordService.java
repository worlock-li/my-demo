package com.wm.service;

import com.wm.pojo.PageResult;
import com.wm.pojo.Record;

import java.util.List;

/**
 * @author wh
 */
public interface RecordService {

    void wareHousing(Record record);

    List<Record> findAll();

    PageResult findPage(Record record, int pageNum, int pageSize);

    PageResult findPage(int pageNum, int pageSize);

    List<Record> findRecord();

    void outStock(Record record);
}
