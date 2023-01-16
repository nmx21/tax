package com.tax.command.impl.user;

import com.tax.command.Command;
import com.tax.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        return "user_profile.jsp";
    }
}
