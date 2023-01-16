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
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
