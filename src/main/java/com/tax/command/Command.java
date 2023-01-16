package com.tax.command;

import com.tax.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface Command {
    String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, SQLException;
}
