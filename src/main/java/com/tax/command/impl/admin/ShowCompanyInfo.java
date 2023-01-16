package com.tax.command.impl;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.db.entity.Company;
import com.tax.db.entity.Report;
import com.tax.logic.CompanyManager;
import com.tax.logic.ReportManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCompanyInfo implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        Company company = CompanyManager.getInstance().findCompanyById(Integer.parseInt(req.getParameter("id")));
        req.getSession().removeAttribute("company_info");
        req.getSession().setAttribute("company_info", company);
        List<Report> reports = ReportManager.getInstance().findReportByCompanyId(Integer.parseInt(req.getParameter("id")));
        req.getSession().removeAttribute("reports");
        req.getSession().setAttribute("reports", reports);
        return "admin_show_company_info.jsp";
    }
}
