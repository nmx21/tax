package com.tax.db.entity;

import java.io.Serializable;

public class CompanyType implements Serializable {
    int id;
    String type;

    public CompanyType() {
    }

    public CompanyType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CompanyType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
