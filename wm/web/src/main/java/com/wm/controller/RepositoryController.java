package com.wm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wm.pojo.Customer;
import com.wm.pojo.PageResult;
import com.wm.pojo.Repository;
import com.wm.pojo.Result;
import com.wm.service.RepositoryService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author wh
 */
@RestController
@RequestMapping("/repository")
public class RepositoryController {
    @Reference
    private RepositoryService service;

    @RequestMapping("/findAll.do")
    public List<Repository> findAll() {
        return service.findAll();
    }

    @RequestMapping("/search.do")
    public PageResult search(String searchInfo, int pageNum, int pageSize) throws UnsupportedEncodingException {
        String name = null;
        if (searchInfo != null && searchInfo.trim().length() != 0) {
            name = new String(searchInfo.getBytes("ISO-8859-1"),"UTF-8");
        }
        return service.findPage(name, pageNum, pageSize);
    }

    @RequestMapping("/findOneById.do")
    public Repository findOneById(int id) {
        return service.findOneById(id);
    }

    @RequestMapping("/delete.do")
    public Result delete(int id) {
        try {
            service.delete(id);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            return new Result(true, "删除失败");
        }
    }

    @RequestMapping("/add")
    public Result add(String address, int capacity, int admin_id, String status1, int id) {
        try {
            String address1 = new String(address.getBytes("ISO-8859-1"),"UTF-8");
            String status = new String(status1.getBytes("ISO-8859-1"),"UTF-8");
            service.add(address1, capacity, admin_id, status);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");
        }
    }

    @RequestMapping("/update")
    public Result update(String address, int capacity, int admin_id, String status1, int id) {
        try {
            String address1 = new String(address.getBytes("ISO-8859-1"),"UTF-8");
            String status = new String(status1.getBytes("ISO-8859-1"),"UTF-8");
            service.update(address1, capacity, admin_id, status ,id);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }
}
