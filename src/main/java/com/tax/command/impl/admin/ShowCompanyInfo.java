package com.tax.command.impl.admin;

import com.tax.command.Command;
import com.tax.db.entity.Company;
import com.tax.exception.DBException;
import com.tax.logic.CompanyManager;
import com.tax.logic.ReportManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class ShowCompanyInfo implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String companyId = req.getParameter("id");
        if (companyId == null || Objects.equals(companyId, "")) {
            req.getSession().setAttribute("message", "ID company can`t be blank!");
            return "admin_list_companies.jsp";
        }
        if (Integer.parseInt(companyId) >= 0) {
            List<Company> companies = (List<Company>) CompanyManager.getInstance().findAllCompany();
            for (Company company : companies) {
                if (company.getId() == Integer.parseInt(companyId)) {
                    req.getSession().setAttribute("company_info", CompanyManager.getInstance().findCompanyById(company.getId()));
                    req.getSession().setAttribute("reports", ReportManager.getInstance().findReportByCompanyId(company.getId()));
                    return "admin_show_company_info.jsp";
                }
            }
        }
        req.getSession().setAttribute("message", "No company with this ID!");
        return "admin_list_companies.jsp";


    }
}
