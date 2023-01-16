package com.tax.command.impl;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.db.entity.Company;
import com.tax.logic.CompanyManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ListCompanyCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        List<Company> companyList = CompanyManager.getInstance().findAllCompany();
        req.getSession().removeAttribute("company");
        req.getSession().setAttribute("company", companyList);
        return "admin_list_companies.jsp";
    }
}
