package com.tax.command.impl.all;

import com.tax.command.Command;
import com.tax.db.entity.User;
import com.tax.exception.DBException;
import com.tax.logic.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    private static final String MESSAGE = "message";
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        if ("".equals(login) || "".equals(password) || "".equals(email)) {
            req.getSession().setAttribute(MESSAGE, "Заповніть усі поля форми");
            return "registrationForm.jsp";
        }
        if (UserManager.getInstance().findUser(login) != null) {
            req.getSession().setAttribute(MESSAGE, "Спробуйте інший логін чи пароль");
            return "registrationForm.jsp";
        }
        UserManager.getInstance().createUsers(new User(login, password, email));
        req.getSession().setAttribute(MESSAGE, "Успішна реєстрація");
        return "index.jsp";
    }
}
