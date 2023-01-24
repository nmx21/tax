package com.tax.db.entity;

import java.io.Serializable;

public class ReportStatus implements Serializable {
    private int id;
    private String type;

    public ReportStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ReportStatus Id cant be < 0 and >"+ Integer.MAX_VALUE);
        }
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.isBlank()) {
            throw new IllegalArgumentException("Type of ReportStatus cant be blank");
        } else if (type.length() > 45) {
            throw new IllegalArgumentException("Illegal type value, current value is long (" + type + ")");
        } else {
            this.type = type;
        }
    }

    @Override
    public String toString() {
        return "ReportStatus{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
