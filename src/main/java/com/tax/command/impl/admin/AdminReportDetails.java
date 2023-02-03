package com.tax.command.impl.admin;

import com.tax.command.Command;
import com.tax.db.entity.Report;
import com.tax.exception.DBException;
import com.tax.logic.ReportManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class AdminReportDetails implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        String reportId = req.getParameter("id");
        if (reportId == null || Objects.equals(reportId, "")) {
            req.getSession().setAttribute("message", "ID report can`t be blank!");
            return "admin_list_reports.jsp";
        }
        if (Integer.parseInt(reportId) >= 0) {
            List<Report> reports = ReportManager.getInstance().findAllReports();
            for (Report report : reports) {
                if (report.getId() == Integer.parseInt(reportId)) {
                    req.getSession().setAttribute("report_info", report);
                    return "admin_show_report_info.jsp";
                }
            }
        }
        req.getSession().setAttribute("message", "Report not found!");
        return "admin_list_reports.jsp";
    }
}
