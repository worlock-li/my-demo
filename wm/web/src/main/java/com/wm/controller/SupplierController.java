package com.wm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wm.pojo.PageResult;
import com.wm.pojo.Result;
import com.wm.pojo.Supplier;
import com.wm.service.SupplierService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author wh
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Reference
    private SupplierService service;

    @RequestMapping("/findAll.do")
    public List<Supplier> findAll() {
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

    @RequestMapping("/delete.do")
    public Result delete(int id) {
        try {
            service.delete(id);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            return new Result(true, "删除失败");
        }
    }

    @RequestMapping("/findOneById.do")
    public Supplier findOneById(int id) {
        return service.findOneById(id);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Supplier supplier) {
        try {
            service.add(supplier);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");
        }
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Supplier supplier) {
        try {
            service.update(supplier);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

}
