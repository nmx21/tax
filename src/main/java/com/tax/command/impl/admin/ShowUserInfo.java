package com.tax.command.impl.admin;

import com.tax.command.Command;
import com.tax.db.entity.User;
import com.tax.exception.DBException;
import com.tax.logic.CompanyManager;
import com.tax.logic.ReportManager;
import com.tax.logic.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowUserInfo implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        if (req.getParameter("id").equals(""))return "error.jsp";
        User user_for_detail = new User();
        user_for_detail.setId(Integer.parseInt(req.getParameter("id")));
        req.getSession().setAttribute("user_info", UserManager.getInstance().findUserById(user_for_detail.getId()));
        req.getSession().setAttribute("company", CompanyManager.getInstance().findCompanyByUserId(user_for_detail.getId()));
        req.getSession().setAttribute("reports", ReportManager.getInstance().findAllReportsByUser(user_for_detail));
        return "show_user_info.jsp";
    }

    private Boolean validate(String id) {
        int id_value = Integer.parseInt(id);
        if (id_value < 0) {
            return false;
        }
        return true;
    }
}
