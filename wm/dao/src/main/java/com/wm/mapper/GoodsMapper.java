package com.wm.mapper;

import com.wm.pojo.Goods;
import com.wm.pojo.PageResult;
import com.wm.pojo.RepositoryDetails;

import java.util.List;

public interface GoodsMapper {

    List<Goods> findAll();

    List<Goods> search(int goods_type_id, String goods_name);

    List<Goods> searchByTypeId(int goods_type_id);

    List<Goods> searchByGoodsName(String goods_name);

    void delete(int id);

    void add(Goods goods);

    Goods findOneById(int id);

    void update(Goods goods);

    List<RepositoryDetails> findDetails(int id, String ids);

    List<Goods> findGoodsByTypeId(int goods_type_id);

    void wareHousing(String whValue, String goods_name, int num);

    List<Goods> findGoodsByCKId(String ckId);

    void outStock(String whValue, String goods_name, int num);
}
