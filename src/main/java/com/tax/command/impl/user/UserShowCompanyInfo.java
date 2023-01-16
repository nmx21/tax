package com.tax.command.impl;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.db.entity.Company;
import com.tax.logic.CompanyManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserShowCompanyInfo implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        Company company = CompanyManager.getInstance().findCompanyById(Integer.parseInt(req.getParameter("id")));
        req.getSession().removeAttribute("company_info");
        req.getSession().setAttribute("company_info", company);
        return "user_show_company_info.jsp";
    }
}
