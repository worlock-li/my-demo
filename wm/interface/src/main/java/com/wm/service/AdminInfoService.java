package com.wm.service;

import com.wm.pojo.PageResult;
import com.wm.pojo.WmAdmin;

import java.util.List;

/**
 * @author wm
 */
public interface AdminInfoService {

    /**
     * find all wm admin info
     * @return
     */
    List<WmAdmin> findAll();

    /**
     * delete admin by id
     * @param id
     */
    void deleteAdmin(int id);

    /**
     * add new wm admin
     * @param wmAdmin
     */
    void add(WmAdmin wmAdmin);

    /**
     * search find one by id
     * @param id
     * @return
     */
    WmAdmin findOneById(int id);

    /**
     * update wm admin
     * @param wmAdmin
     */
    void update(WmAdmin wmAdmin);

    /**
     *  分页查询
     * @param pageNum
     * @param pageSize
     */
    PageResult findPage(int pageNum, int pageSize);

    /**
     *  模糊查询  + 分页
     * @param searchInfo
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult findPage(String searchInfo, int pageNum, int pageSize);

}
