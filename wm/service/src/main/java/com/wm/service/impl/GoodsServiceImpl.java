package com.wm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.mapper.GoodsMapper;
import com.wm.pojo.Goods;
import com.wm.pojo.PageResult;
import com.wm.pojo.RepositoryDetails;
import com.wm.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wh
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper mapper;

    @Override
    public List<Goods> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goodsList = findAll();
        Page<Goods> page = (Page<Goods>) goodsList;

        // 获取当前页的数据
        List<Goods> result = page.getResult();
        //获取总条数
        long total = page.getTotal();
        return new PageResult(total, result);
    }

    @Override
    public PageResult search(String goods_name, int id, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goodsList;
        System.out.println(goods_name);
        if (goods_name != null && goods_name.trim().length() != 0) {
            if (id < 0) {
                goodsList= mapper.searchByGoodsName(goods_name);
            } else {
                goodsList = mapper.search(id, goods_name);
            }
        } else {
            if (id < 0) {
                goodsList = findAll();
            } else {
                goodsList = mapper.searchByTypeId(id);
            }
        }

        Page<Goods> page = (Page<Goods>) goodsList;

        // 获取当前页的数据
        List<Goods> result = page.getResult();
        //获取总条数
        long total = page.getTotal();
        return new PageResult(total, result);

    }

    @Override
    public void delete(int id) {
        mapper.delete(id);
    }

    @Override
    public void add(Goods goods) {
        mapper.add(goods);
    }

    @Override
    public Goods findOneById(int id) {
        return mapper.findOneById(id);
    }

    @Override
    public void update(Goods goods) {
        mapper.update(goods);
    }

    @Override
    public List<RepositoryDetails> findDetails(int id) {
        String ids = "%"+id+"%";
        return mapper.findDetails(id, ids);
    }

    @Override
    public List<Goods> findGoodsByTypeId(int id) {
        return mapper.findGoodsByTypeId(id);
    }

    @Override
    public void wareHousing(String whValue, String goods_name, int num) {
        mapper.wareHousing(whValue, goods_name, num);
    }

    @Override
    public List<Goods> findGoodsByCKId(int ckId) {
        return mapper.findGoodsByCKId("%"+ckId+"%");
    }

    @Override
    public void outStock(String whValue, String goods_name, int num) {
        mapper.outStock(whValue, goods_name, num);
    }

}
