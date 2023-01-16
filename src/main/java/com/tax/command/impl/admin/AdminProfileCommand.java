package com.tax.command.impl.admin;

import com.tax.command.Command;
import com.tax.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        return "admin_profile.jsp";
    }
}
