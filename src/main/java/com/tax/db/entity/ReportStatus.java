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
        if (id >= 0) {
            this.id = id;
        } else throw new IllegalArgumentException("ReportStatus Id cant be < 0");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!type.isBlank()) {
            this.type = type;
        } else throw new IllegalArgumentException("Type of ReportStatus cant be blank");
    }

    @Override
    public String toString() {
        return "ReportStatus{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
