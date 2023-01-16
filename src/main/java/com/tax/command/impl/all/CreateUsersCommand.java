package com.tax.command.impl;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.db.entity.User;
import com.tax.logic.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        User u1 = new User();
        u1.setUsername(req.getParameter("login1"));
        u1.setPassword(req.getParameter("password1"));
        UserManager.getInstance().createUsers(u1);

        return "controller?command=listUsers";
    }
}
