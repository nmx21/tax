package com.tax.db.entity;

import java.io.Serializable;

public class ReportType implements Serializable {
    int id;
    String type;
    CompanyType companyTypeId;

    public ReportType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id >= 0) {
            this.id = id;
        } else throw new IllegalArgumentException("ReportType Id cant be < 0");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!type.isBlank()) {
            this.type = type;
        } else throw new IllegalArgumentException("Type of ReportType cant be blank");
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
