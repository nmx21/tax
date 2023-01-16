package com.tax.command.impl;

import com.tax.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements com.tax.command.Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        req.getSession().setAttribute("message", "You are logout");
        req.getSession().removeAttribute("currentUser");
        req.getSession().removeAttribute("isAuth");
        req.getSession().removeAttribute("status");
        req.getSession().removeAttribute("company");
        req.getSession().removeAttribute("company_data");
        req.getSession().removeAttribute("user_info");
        return "index.jsp";
    }
}
