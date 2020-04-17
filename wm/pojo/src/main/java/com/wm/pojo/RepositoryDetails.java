package com.wm.pojo;

import java.io.Serializable;

public class RepositoryDetails implements Serializable {

    private int capacity;
    private String wh_value;
    private String goods_name;

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getWh_value() {
        return wh_value;
    }

    public void setWh_value(String wh_value) {
        this.wh_value = wh_value;
    }
}
