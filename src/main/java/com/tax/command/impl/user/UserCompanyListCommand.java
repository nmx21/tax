package com.tax.command.impl;

import com.tax.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCompanyListCommand implements com.tax.command.Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        return "user_company_list.jsp";
    }
}
