package com.wm.pojo;

import java.io.Serializable;

/**
 * @author Lxf
 */
public class WmAdmin implements Serializable {
    private int id;
    private String username;
    private String sex;
    private String phone;
    private String wm_id;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWm_id(String wm_id) {
        this.wm_id = wm_id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getSex() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    public String getWm_id() {
        return wm_id;
    }

    public WmAdmin() {
    }

    @Override
    public String toString() {
        return "WmAdmin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", wm_id='" + wm_id + '\'' +
                '}';
    }
}
