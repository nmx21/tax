package com.tax.db.entity;

import java.io.Serializable;

public class CompanyType implements Serializable {
    private int id;
    private String type;

    public CompanyType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Company type Id cant be < 0 and >" + Integer.MAX_VALUE);
        }
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.isBlank()) {
            throw new IllegalArgumentException("Illegal company type value, current value is blank");
        } else if (type.length() > 45) {
            throw new IllegalArgumentException("Illegal company type value, current value is long (" + type + ")");
        } else {
            this.type = type;
        }
    }

    @Override
    public String toString() {
        return "CompanyType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
