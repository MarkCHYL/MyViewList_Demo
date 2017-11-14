package com.view.demo.bean;

import java.util.List;

/**
 * 实体类-省份
 * Created by Mark on 2017/11/13.
 */

public class ProvinceModel {
    private String id;
    private String name;
    private List<CityModel> child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityModel> getChild() {
        return child;
    }

    public void setChild(List<CityModel> child) {
        this.child = child;
    }
}
