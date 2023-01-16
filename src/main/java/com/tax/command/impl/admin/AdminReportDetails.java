package com.tax.command.impl.admin;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.logic.ReportManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminReportDetails implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        req.getSession().setAttribute("report_info", ReportManager.getInstance().findReportById(Integer.parseInt(req.getParameter("id"))));
        return "admin_show_report_info.jsp";
    }
}
