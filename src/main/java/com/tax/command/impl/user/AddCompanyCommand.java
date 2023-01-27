package com.tax.command.impl.user;

import com.tax.command.Command;
import com.tax.db.entity.Address;
import com.tax.db.entity.Company;
import com.tax.db.entity.User;
import com.tax.exception.DBException;
import com.tax.logic.CompanyManager;
import com.tax.logic.CompanyTypeManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class AddCompanyCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        Address address = new Address();
        address.setCountry(req.getParameter("country"));
        address.setRegion(req.getParameter("region"));
        address.setCity(req.getParameter("city"));
        address.setStreet(req.getParameter("street"));
        address.setBuilding(req.getParameter("building"));
        address.setBuildingLetter(req.getParameter("buildingLetter"));
        address.setOffice(req.getParameter("office"));
        address.setOfficeLetter(req.getParameter("officeLetter"));

        Company company = new Company();
        company.setType(CompanyTypeManager.getInstance().findCompanyTypeById(Integer.parseInt(req.getParameter("companyType"))));
        company.setName(req.getParameter("name"));
        company.setInn(req.getParameter("inn"));
        company.setAddress(address);
        User curentUser = (User) req.getSession().getAttribute("currentUser");
        CompanyManager.getInstance().createCompanies(curentUser, company);

        return "controller?command=userCompanyList";
    }
}
