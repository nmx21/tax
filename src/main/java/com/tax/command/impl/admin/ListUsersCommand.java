package com.tax.command.impl;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.db.entity.User;
import com.tax.logic.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ListUsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        List<User> users = UserManager.getInstance().findAllUsers();
        req.getSession().removeAttribute("users");
        req.getSession().setAttribute("users", users);
        return "admin_list_users.jsp";
    }
}
