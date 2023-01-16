package com.tax.command.impl.user;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.logic.CompanyManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserShowCompanyInfo implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        req.getSession().setAttribute("company_info", CompanyManager.getInstance().findCompanyById(Integer.parseInt(req.getParameter("id"))));
        return "user_show_company_info.jsp";
    }
}
