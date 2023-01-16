package com.tax.db.entity;

import java.io.Serializable;

public class ReportStatus implements Serializable {
    int id;
    String type;

    public ReportStatus() {
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
        return "ReportStatus{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
