package com.tax.command.impl.user;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.logic.ReportManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserReportDetails implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        req.getSession().setAttribute("report_info", ReportManager.getInstance().findReportById(Integer.parseInt(req.getParameter("id"))));
        return "user_show_report_info.jsp";
    }
}
