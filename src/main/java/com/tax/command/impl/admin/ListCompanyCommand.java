package com.tax.command.impl.admin;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.logic.CompanyManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ListCompanyCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        req.getSession().setAttribute("company", CompanyManager.getInstance().findAllCompany());
        return "admin_list_companies.jsp";
    }
}
