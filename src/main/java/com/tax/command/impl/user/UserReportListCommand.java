package com.tax.command.impl.user;

import com.tax.db.entity.User;
import com.tax.exception.DBException;
import com.tax.logic.ReportManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UserReportListCommand implements com.tax.command.Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        req.getSession().setAttribute("reports", ReportManager.getInstance().findAllReportsByUser((User) req.getSession().getAttribute("currentUser")));
        return "user_report_list_show.jsp";
    }
}
