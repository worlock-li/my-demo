package com.wm.pojo;

import java.io.Serializable;

public class GoodsType implements Serializable {
    private int id;
    private String goods_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }
}
