package com.tax.db.entity;

import java.io.Serializable;
import java.sql.Date;

public class Report implements Serializable {
    private int id;
    private ReportType reportType;
    private int financialIncome;
    private int taxAmount;
    private String description;
    private String comment;
    private Date dateCreate;
    private Date dateLastEdit;
    private ReportStatus reportStatus;
    private Company company;
    private User user;
    private String reportBody;

    public Report() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Report Id cant be < 0 and >"+ Integer.MAX_VALUE);
        }
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
        if (financialIncome < 0) {
            throw new IllegalArgumentException("Financial Income cant be < 0 and >"+ Integer.MAX_VALUE);
        }
        this.financialIncome = financialIncome;
    }

    public int getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(int taxAmount) {
        if (taxAmount < 0) {
            throw new IllegalArgumentException("Tax Amount cant be < 0 and >"+ Integer.MAX_VALUE);
        }
        this.taxAmount = taxAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() > 254) {
            throw new IllegalArgumentException("Illegal description value, current value is long (" + description + ")");
        } else {
            this.description = description;
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        if (comment.length() > 10000) {
            throw new IllegalArgumentException("Illegal comment value, current value is long (more than 10001 symbol)");
        } else {
            this.comment = comment;
        }
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
