package com.tax.command.impl;

import com.tax.command.Command;
import com.tax.exception.DBException;
import com.tax.db.entity.User;
import com.tax.logic.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProfileCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        User user = new User();
        User currentUser;
        currentUser = (User) req.getSession().getAttribute("currentUser");
        user.setId(currentUser.getId());
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));

        if (currentUser.getEmail().equals(user.getEmail()) || (currentUser.getPassword().equals(user.getPassword()) && "".equals(user.getPassword()))) {
            //System.out.println("Old data not new data...");
        } else {
            UserManager.getInstance().updateUser(user);
        }
        if ("admin".equals(req.getSession().getAttribute("status")))
            return "controller?command=adminProfile";
        return "controller?command=userProfile";
    }
}
