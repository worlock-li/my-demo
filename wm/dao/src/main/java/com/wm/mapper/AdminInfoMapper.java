package com.wm.mapper;

import com.wm.pojo.WmAdmin;
import java.util.List;

/**
 * @author wm
 */
public interface AdminInfoMapper {

    /**
     * find all admin info
     * @return
     */
    List<WmAdmin> findAll();

    /**
     * delete admin by id
     * @param id
     */
    void deleteAdmin(int id);

    void add(WmAdmin wmAdmin);

    WmAdmin findOneById(int id);

    void update(WmAdmin wmAdmin);

    List<WmAdmin> searchByUsername(String searchInfo);
}
