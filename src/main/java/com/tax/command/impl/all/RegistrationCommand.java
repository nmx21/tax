package com.tax.command.impl;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.db.entity.User;
import com.tax.logic.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        if ("".equals(login) || "".equals(password) || "".equals(email)){
            req.getSession().setAttribute("message", "Заповніть усі поля форми");
            return "registrationForm.jsp";
        }
        // todo check to find dublicate
        UserManager.getInstance().createUsers(new User(login,password, email));
        req.getSession().setAttribute("message", "Успішна реєстрація");
        return "index.jsp";


    }
}
