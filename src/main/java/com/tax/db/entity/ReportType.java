package com.tax.db.entity;

import java.io.Serializable;

public class ReportType implements Serializable {
    private int id;
    private String type;
    private CompanyType companyTypeId;

    public ReportType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ReportType Id cant be < 0 and >" + Integer.MAX_VALUE);
        }
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.isBlank()) {
            throw new IllegalArgumentException("Type of ReportType cant be blank");
        } else if (type.length() > 45) {
            throw new IllegalArgumentException("Illegal type value, current value is long (" + type + ")");
        } else {
            this.type = type;
        }
    }

    public CompanyType getCompanyTypeId() {
        return companyTypeId;
    }

    public void setCompanyTypeId(CompanyType companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    @Override
    public String toString() {
        return "ReportType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", companyTypeId=" + companyTypeId +
                '}';
    }
}
