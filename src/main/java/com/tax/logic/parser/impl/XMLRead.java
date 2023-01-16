package com.tax.logic.parser;

import com.tax.db.entity.Company;
import com.tax.db.entity.Report;
import com.tax.db.entity.ReportType;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;

public class XMLRead implements FileReport {
    @Override
    public Report parseFile(String fileName) throws IOException, ParseException {
        Report fileReport = new Report();
        try {
            File file = new File(fileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("report");
            Company company = new Company();
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    company.setId(Integer.parseInt(eElement.getElementsByTagName("company_data_id").item(0).getTextContent()));

                    ReportType reportType = new ReportType();
                    reportType.setId(Integer.parseInt(eElement.getElementsByTagName("report_type_id").item(0).getTextContent()));

                    fileReport.setCompany(company);
                    fileReport.setReportType(reportType);
                    fileReport.setFinancialIncome(Integer.parseInt(eElement.getElementsByTagName("financial_income").item(0).getTextContent()));
                    fileReport.setTaxAmount(Integer.parseInt(eElement.getElementsByTagName("tax_amount").item(0).getTextContent()));
                    fileReport.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileReport;
    }
}