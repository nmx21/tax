package com.tax.command.impl.user;

import com.tax.command.Command;
import com.tax.db.entity.Report;
import com.tax.db.entity.User;
import com.tax.exception.DBException;
import com.tax.logic.ReportManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class UserReportDetails implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String reportId = req.getParameter("id");
        if (reportId == null || Objects.equals(reportId, "")) {
            req.getSession().setAttribute("message", "ID report can`t be blank!");
            return "user_report_list_show.jsp";
        }
        if (Integer.parseInt(reportId) >= 0) {
            User user = (User) req.getSession().getAttribute("currentUser");
            List<Report> reports = (List<Report>) ReportManager.getInstance().findAllReportsByUser(user);
            for (Report report : reports) {
                if (report.getId() == Integer.parseInt(reportId)) {
                    req.getSession().setAttribute("report_info", report);
                    return "user_show_report_info.jsp";
                }
            }
        }
        req.getSession().setAttribute("message", "Access denied!");
        return "user_report_list_show.jsp";
    }
}
