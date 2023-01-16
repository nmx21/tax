package com.tax.command.impl;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.db.entity.Report;
import com.tax.logic.ReportManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UserReportDetails implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        Report report = ReportManager.getInstance().findReportById(Integer.parseInt(req.getParameter("id")));
        req.getSession().removeAttribute("report_info");
        req.getSession().setAttribute("report_info", report);
        return "user_show_report_info.jsp";
    }
}
