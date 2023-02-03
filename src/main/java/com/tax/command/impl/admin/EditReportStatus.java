package com.tax.command.impl.admin;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.logic.ReportManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class EditReportStatus implements Command {
    private static final Logger log = LoggerFactory.getLogger(EditReportStatus.class.getName());
    private static final String OPERATION = "operation";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        if (req.getParameter(OPERATION).equals("accept") || req.getParameter(OPERATION).equals("reject")) {
            ReportManager.getInstance().changeStatusReport(req.getParameter(OPERATION), Integer.parseInt(req.getParameter("reportId")), req.getParameter("comment"));
        } else {
            log.error("There are no command to change status");
        }
        return "controller?command=adminReportDetails&id=" + req.getParameter("reportId");
    }
}
