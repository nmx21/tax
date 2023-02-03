package com.tax.command.impl.admin;

import com.tax.command.Command;
import com.tax.db.entity.User;
import com.tax.exception.DBException;
import com.tax.logic.CompanyManager;
import com.tax.logic.ReportManager;
import com.tax.logic.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class ShowUserInfo implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String userId = req.getParameter("id");
        if (userId == null || Objects.equals(userId, "")) {
            req.getSession().setAttribute("message", "ID user can`t be blank!");
            return "admin_list_users.jsp";
        }
        if (Integer.parseInt(userId) > 0) {
            User user = new User();
            user.setId(Integer.parseInt(userId));
            List<User> users = UserManager.getInstance().findAllUsers();
            for (User tempUser : users) {
                if (tempUser.getId() == user.getId()) {
                    req.getSession().setAttribute("user_info", UserManager.getInstance().findUserById(tempUser.getId()));
                    req.getSession().setAttribute("company", CompanyManager.getInstance().findCompanyByUserId(tempUser.getId()));
                    req.getSession().setAttribute("reports", ReportManager.getInstance().findAllReportsByUser(tempUser));
                    return "show_user_info.jsp";
                }
            }
        }
        req.getSession().setAttribute("message", "No user with this ID!");
        return "admin_list_users.jsp";
    }
}
