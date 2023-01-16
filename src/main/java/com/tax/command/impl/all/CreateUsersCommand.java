package com.tax.command.impl.all;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.db.entity.User;
import com.tax.logic.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        UserManager.getInstance().createUsers(new User(req.getParameter("login"),req.getParameter("password")));
        return "controller?command=listUsers";
    }
}
