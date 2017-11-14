package com.view.demo.bean;

import java.util.List;

/**
 * 实体类-城市
 * Created by Mark on 2017/11/13.
 */

public class CityModel {
    private String id;
    private String name;
    private String pid;
    private List<DistrictModel> child;

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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<DistrictModel> getChild() {
        return child;
    }

    public void setChild(List<DistrictModel> child) {
        this.child = child;
    }
}
