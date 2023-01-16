package com.tax.command.impl;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.logic.ReportManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class EditReportStatus implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        if (req.getParameter("operation").equals("accept") || req.getParameter("operation").equals("reject")) {
            int status = ReportManager.getInstance().changeStatusReport(req.getParameter("operation"), Integer.parseInt(req.getParameter("reportId")), req.getParameter("comment"));
        } else {
            System.out.println("There are no command to change status");
        }
        // TODO for all if not present command

        return "controller?command=adminReportDetails&id=" + req.getParameter("reportId");
    }
}
