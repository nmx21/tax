package com.tax.command.impl.all;

import com.tax.command.Command;
import com.tax.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        return "registrationForm.jsp";
    }
}
