package com.tax.command.impl;

import com.tax.exception.DBException;
import com.tax.db.entity.Report;
import com.tax.db.entity.User;
import com.tax.logic.ReportManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserReportListCommand implements com.tax.command.Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        List<Report> reports = ReportManager.getInstance().findAllReportsByUser(currentUser);
        req.getSession().setAttribute("reports", reports);
        return "user_report_list_show.jsp";
    }
}
