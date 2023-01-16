package com.tax.command.impl.admin;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.logic.ReportManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListReportCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        req.getSession().setAttribute("reports", ReportManager.getInstance().findAllReports());
        return "admin_list_reports.jsp";
    }
}
