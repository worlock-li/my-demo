package com.wm.service;

import com.wm.pojo.Goods;
import com.wm.pojo.PageResult;
import com.wm.pojo.RepositoryDetails;

import java.util.List;

/**
 * @author wh
 */
public interface GoodsService {
    /**
     * find all goods
     * @return
     */
    List<Goods> findAll();

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult findPage(int pageNum, int pageSize);

    PageResult search(String goods_name, int id, int pageNum, int pageSize);

    void delete(int id);

    void add(Goods goods);

    Goods findOneById(int id);

    void update(Goods goods);

    List<RepositoryDetails> findDetails(int id);

    List<Goods> findGoodsByTypeId(int id);

    void wareHousing(String whValue, String goods_name, int num);

    List<Goods> findGoodsByCKId(int ckId);

    void outStock(String whValue, String goods_name, int num);
}
