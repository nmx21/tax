package com.tax.command.impl.all;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.db.entity.User;
import com.tax.logic.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CreateUsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        if (login.isBlank()||password.isBlank()|| email.isBlank()){

            return "controller?command=registrationForm";
        }
        User user = new User(login,password,email);
        UserManager.getInstance().createUsers(user);
        return "controller?command=listUsers";
    }
}
