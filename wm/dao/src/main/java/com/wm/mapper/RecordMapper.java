package com.wm.mapper;

import com.wm.pojo.Record;

import java.util.Date;
import java.util.List;

/**
 * @author wh
 */
public interface RecordMapper {

    void wareHousing(Record record);

    List<Record> findAll();

    List<Record> search(int repositoryId, Date changeTime);

    List<Record> searchByReId(int repositoryId);

    List<Record> searchByTime(Date changeTime);

    List<Record> findRecord();

    void outStock(Record record);
}
