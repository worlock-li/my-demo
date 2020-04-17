package com.wm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wm.pojo.*;
import com.wm.service.GoodsService;
import com.wm.service.RecordService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

/**
 * @author wh
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Reference
    private GoodsService service;
    @Reference
    private RecordService recordService;

    @RequestMapping("/findAll.do")
    public List<Goods> findAll() {
        return service.findAll();
    }

    @RequestMapping("/findPage.do")
    public PageResult findPage(int pageNum, int pageSize) {
        return service.findPage(pageNum, pageSize);
    }

    @RequestMapping("/search.do")
    public PageResult search(String goods_name, int id, int pageNum, int pageSize) throws UnsupportedEncodingException {
        String name = null;
        if (goods_name != null && goods_name.trim().length() != 0) {
            name = new String(goods_name.getBytes("ISO-8859-1"),"UTF-8");
        }
        return service.search(name, id, pageNum, pageSize);
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

    /**
     * 进行文件的上传
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String imgUpload(CommonsMultipartFile multipartFile) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        int index = filename.lastIndexOf(".");
        String extName = filename.substring(index);
        String newName = UUID.randomUUID() + extName;

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String base = request.getSession().getServletContext().getRealPath("/goodsImage//");
        String imgUrl = "http://localhost:9101/goodsImage/"+newName;
        // 指定存放的目录
        File file = new File(base, newName);
        multipartFile.transferTo(file);
        return imgUrl;
    }

    @RequestMapping("/add.do")
    public Result add(@RequestParam(value = "file") CommonsMultipartFile multipartFile, @RequestParam String pojo) {
        try {
            String imgUrl = imgUpload(multipartFile);
            ObjectMapper objectMapper = new ObjectMapper();
            Goods goods = objectMapper.readValue(pojo, Goods.class);
            goods.setGoods_img(imgUrl);
            service.add(goods);
            return new Result(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");
        }
    }

    @RequestMapping("/findOneById")
    public Goods findOneById(int id) {
        return service.findOneById(id);
    }

    @RequestMapping("/update.do")
    public Result update(@RequestParam(value = "file") CommonsMultipartFile multipartFile, @RequestParam String pojo) {
        try {
            String imgUrl = imgUpload(multipartFile);
            ObjectMapper objectMapper = new ObjectMapper();
            Goods goods = objectMapper.readValue(pojo, Goods.class);
            if (imgUrl != null) {
                goods.setGoods_img(imgUrl);
            } else {
                Goods oneById = findOneById(goods.getId());
                goods.setGoods_img(oneById.getGoods_img());
            }
            service.update(goods);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("/findDetails.do")
    public List<RepositoryDetails> findDetails(int id) {
        return service.findDetails(id);
    }

    @RequestMapping("/findGoodsByTypeId.do")
    public List<Goods> findGoodsByTypeId(int id) {
        return service.findGoodsByTypeId(id);
    }

    @RequestMapping("/wareHousing.do")
    public Result wareHousing(String whValue, String goods_name, int num, @RequestBody Record record) {
        try {
            goods_name = new String(goods_name.getBytes("ISO-8859-1"),"UTF-8");
            service.wareHousing(whValue, goods_name, num);
            recordService.wareHousing(record);
            return new Result(true, "入库成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "入库失败");
        }
    }

    @RequestMapping("/findGoodsByCKId.do")
    public List<Goods> findGoodsByCKId(int id) {
        return service.findGoodsByCKId(id);
    }

    @RequestMapping("/outStock.do")
    public Result outStock(String whValue, String goods_name, int num, @RequestBody Record record) {
        try {
            goods_name = new String(goods_name.getBytes("ISO-8859-1"),"UTF-8");
            service.outStock(whValue, goods_name, num);
            recordService.outStock(record);
            return new Result(true, "出库成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "出库失败");
        }
    }

}
