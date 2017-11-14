package com.view.demo.bean;

import java.io.Serializable;

/**
 * 实体类-区（县）
 * Created by Mark on 2017/11/13.
 */

public class DistrictModel implements Serializable
{
    private String id;
    private String name;

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
}
