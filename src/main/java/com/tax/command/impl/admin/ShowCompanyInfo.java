package com.tax.command.impl.admin;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.logic.CompanyManager;
import com.tax.logic.ReportManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowCompanyInfo implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        req.getSession().setAttribute("company_info", CompanyManager.getInstance().findCompanyById(Integer.parseInt(req.getParameter("id"))));
        req.getSession().setAttribute("reports", ReportManager.getInstance().findReportByCompanyId(Integer.parseInt(req.getParameter("id"))));
        return "admin_show_company_info.jsp";
    }
}
