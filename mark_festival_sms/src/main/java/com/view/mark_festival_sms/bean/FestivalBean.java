package com.view.mark_festival_sms.bean;

import java.util.Date;

/**
 * 节日的实体类
 * Created by mark on 2017/10/14.
 */

public class FestivalBean {
    private int id;
    private String name;
    private String desc;
    private Date date;

    public FestivalBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public FestivalBean(int id, String name, String desc, Date date) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.date = date;
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FestivalBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", date=" + date +
                '}';
    }
}
