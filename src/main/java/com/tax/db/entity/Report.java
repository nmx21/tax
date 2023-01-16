package com.tax.db.entity;

import java.io.Serializable;
import java.sql.Date;

public class Report implements Serializable {
    int id;
    ReportType reportType;
    int financialIncome;
    int taxAmount;
    String description;
    String comment;
    Date dateCreate;
    Date dateLastEdit;
    ReportStatus reportStatus;
    Company company;
    User user;
    String reportBody;

    public Report() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public int getFinancialIncome() {
        return financialIncome;
    }

    public void setFinancialIncome(int financialIncome) {
        this.financialIncome = financialIncome;
    }

    public int getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(int taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateLastEdit() {
        return dateLastEdit;
    }

    public void setDateLastEdit(Date dateLastEdit) {
        this.dateLastEdit = dateLastEdit;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReportBody() {
        return reportBody;
    }

    public void setReportBody(String reportBody) {
        this.reportBody = reportBody;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reportType=" + reportType +
                ", financialIncome=" + financialIncome +
                ", taxAmount=" + taxAmount +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                ", dateCreate=" + dateCreate +
                ", dateLast_edit=" + dateLastEdit +
                ", reportStatusId=" + reportStatus +
                ", companyDataId=" + company +
                ", user=" + user +
                ", reportBody='" + reportBody + '\'' +
                '}';
    }
}
