package com.wm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wm.pojo.GoodsType;
import com.wm.service.GoodsTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wh
 */
@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {
    @Reference
    private GoodsTypeService service;

    @RequestMapping("/findGoodsType.do")
    public List<GoodsType> findGoodsType() {
        return service.findGoodsType();
    }
}
