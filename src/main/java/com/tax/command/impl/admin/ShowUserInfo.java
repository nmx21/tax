package com.tax.command.impl;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.db.entity.Report;
import com.tax.db.entity.User;
import com.tax.logic.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ShowUserInfo implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        User user = UserManager.getInstance().findUserById(Integer.parseInt(req.getParameter("id")));
        req.getSession().removeAttribute("user_info");
        req.getSession().setAttribute("user_info", user);
        List<Report> reports = UserManager.getInstance().findAllReports();
        req.getSession().removeAttribute("reports");
        req.getSession().setAttribute("reports", reports);
        return "show_user_info.jsp";
    }
}
