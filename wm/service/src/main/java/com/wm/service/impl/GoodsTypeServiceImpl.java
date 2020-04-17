package com.wm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wm.mapper.GoodsTypeMapper;
import com.wm.pojo.GoodsType;
import com.wm.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wh
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper mapper;

    @Override
    public List<GoodsType> findGoodsType() {
        return mapper.findGoodsType();
    }
}
