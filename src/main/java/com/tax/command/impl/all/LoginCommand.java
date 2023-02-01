package com.tax.command.impl.all;

import com.tax.command.Command;
import com.tax.db.entity.User;
import com.tax.exception.DBException;
import com.tax.logic.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp)
            throws DBException {
        req.getSession().removeAttribute("message");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = UserManager.getInstance().findUser(login);
        if ( user == null){
            req.getSession().setAttribute("message", "Illegal auth");
            return "index.jsp";
        }
        if (user.getPassword().equals(password)) {
            req.getSession().setAttribute("currentUser", user);
            req.getSession().setAttribute("isAuth", "true");
            UserManager.getInstance().addLoginTime(user);
            if (user.getRoleId().equals(1)) {
                req.getSession().setAttribute("status", "admin");
                return "admin_page.jsp";
            } else {
                req.getSession().setAttribute("status", "user");
                return "client_page.jsp";
            }
        }
        req.getSession().setAttribute("message", "Illegal auth");
        return "index.jsp";
    }
}
