package com.tax.command.impl;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.db.entity.Report;
import com.tax.logic.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ListReportCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        List<Report> reports = UserManager.getInstance().findAllReports();
        req.getSession().removeAttribute("reports");
        req.getSession().setAttribute("reports", reports);
        return "admin_list_reports.jsp";
    }
}
