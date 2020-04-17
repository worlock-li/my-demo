package com.wm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wm.pojo.PageResult;
import com.wm.pojo.Result;
import com.wm.pojo.WmAdmin;
import com.wm.service.AdminInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wm
 */
@RestController
@RequestMapping("/adminInfo")
public class AdminInfoController {

    @Reference
    private AdminInfoService adminInfoService;

    @RequestMapping("/findAll.do")
    public List<WmAdmin> findAll() {
        return adminInfoService.findAll();
    }

    @RequestMapping("/delete.do")
    public void deleteAdmin(int id) {
        adminInfoService.deleteAdmin(id);
    }

    @RequestMapping("/add.do")
    public Result add(@RequestBody WmAdmin wmAdmin) {
        try {
            adminInfoService.add(wmAdmin);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");
        }
    }

    @RequestMapping("/update.do")
    public Result update(@RequestBody WmAdmin wmAdmin) {
        try {
            adminInfoService.update(wmAdmin);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("/findOneById.do")
    public WmAdmin findOneById(int id) {
        return adminInfoService.findOneById(id);
    }

    @RequestMapping("/findPage.do")
    public PageResult findPage(int pageNum, int pageSize) {
        return adminInfoService.findPage(pageNum, pageSize);
    }

    @RequestMapping("/search.do")
    public PageResult search(String searchInfo, int pageNum, int pageSize) {
        return adminInfoService.findPage(searchInfo, pageNum, pageSize);
    }


}
