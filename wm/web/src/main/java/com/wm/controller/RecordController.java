package com.wm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wm.pojo.Customer;
import com.wm.pojo.PageResult;
import com.wm.pojo.Record;
import com.wm.pojo.Result;
import com.wm.service.RecordService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author wh
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Reference
    private RecordService service;

    public Result wareHousing(Record record) {
        try {
            service.wareHousing(record);
            return new Result(true, "入库成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "入库失败");
        }
    }

    @RequestMapping("/findAll.do")
    public List<Record> findAll() {
        return service.findAll();
    }

    @RequestMapping("/search.do")
    public PageResult search(int pageNum, int pageSize, @RequestBody Record record) {
        return service.findPage(record, pageNum, pageSize);
    }

    @RequestMapping("/findRecord.do")
    public List<Record> findRecord() {
        return service.findRecord();
    }

}
