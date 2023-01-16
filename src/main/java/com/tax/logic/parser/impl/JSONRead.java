package com.tax.logic.parser.impl;

import com.tax.db.entity.Company;
import com.tax.db.entity.Report;
import com.tax.db.entity.ReportType;
import com.tax.logic.parser.FileReport;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JSONRead implements FileReport {
    public Report parseFile(String fileName) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(fileName));

        JSONObject jo = (JSONObject) obj;
        Report fileReport = new Report();

        Company company = new Company();
        company.setId((Integer) jo.get("company_data_id"));

        ReportType reportType = new ReportType();
        reportType.setId((Integer) jo.get("report_type_id"));

        fileReport.setCompany(company);
        fileReport.setReportType(reportType);
        fileReport.setFinancialIncome((Integer) jo.get("financial_income"));
        fileReport.setTaxAmount((Integer) jo.get("tax_amount"));
        fileReport.setDescription((String) jo.get("description"));

        return fileReport;
    }
}