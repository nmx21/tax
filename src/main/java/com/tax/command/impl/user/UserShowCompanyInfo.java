package com.tax.command.impl.user;

import com.tax.command.Command;
import com.tax.db.entity.Company;
import com.tax.db.entity.User;
import com.tax.exception.DBException;
import com.tax.logic.CompanyManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UserShowCompanyInfo implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException {
        String companyId = req.getParameter("id");
        if (companyId == null || Objects.equals(companyId, "")) {
            req.getSession().setAttribute("message", "ID company can`t be blank!");
            return "user_company_list.jsp";
        }
        if (Integer.parseInt(companyId) >= 0) {
            User user = (User) req.getSession().getAttribute("currentUser");
            List<Company> companies = CompanyManager.getInstance().findCompanyByUserId(user.getId());
            for (Company company : companies) {
                if (company.getId() == Integer.parseInt(companyId)) {
                    req.getSession().setAttribute("company_info", company);
                    return "user_show_company_info.jsp";
                }
            }
        }
        req.getSession().setAttribute("message", "Access denied!");
        return "user_company_list.jsp";
    }
}
