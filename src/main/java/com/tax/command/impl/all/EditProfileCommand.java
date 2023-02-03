package com.tax.command.impl.all;

import com.tax.command.Command;
import com.tax.db.entity.User;
import com.tax.exception.DBException;
import com.tax.logic.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class EditProfileCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        User user = new User();
        User currentUser;
        currentUser = (User) req.getSession().getAttribute("currentUser");
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        if (!"".equals(req.getParameter("email"))) {
            user.setEmail(req.getParameter("email"));
        }
        if (!"".equals(req.getParameter("password"))) {
            user.setPassword(req.getParameter("password"));
        }
        if (!currentUser.getEmail().equals(user.getEmail()) && (!currentUser.getPassword().equals(user.getPassword()) || !"".equals(user.getPassword()))) {
            UserManager.getInstance().updateUser(user);
            req.getSession().setAttribute("currentUser", user);
        }
        if ("admin".equals(req.getSession().getAttribute("status")))
            return "controller?command=adminProfile";
        return "controller?command=userProfile";
    }
}
