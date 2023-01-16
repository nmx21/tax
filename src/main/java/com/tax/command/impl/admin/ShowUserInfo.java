package com.tax.command.impl.admin;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.logic.ReportManager;
import com.tax.logic.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowUserInfo implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        req.getSession().setAttribute("user_info", UserManager.getInstance().findUserById(Integer.parseInt(req.getParameter("id"))));
        req.getSession().setAttribute("reports", ReportManager.getInstance().findAllReports());
        return "show_user_info.jsp";
    }
}
