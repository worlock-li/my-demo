package com.wm.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wh
 */
public class Supplier implements Serializable {
    private int id;
    private String name;
    private String charge_person;
    private String phone;
    private Date create_time;
    private Date update_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharge_person() {
        return charge_person;
    }

    public void setCharge_person(String charge_person) {
        this.charge_person = charge_person;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
