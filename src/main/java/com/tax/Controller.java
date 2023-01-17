package com.tax;

import com.tax.command.Command;
import com.tax.command.impl.CommandContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(Controller.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String address = "error.jsp";
        String commandName = req.getParameter("command");

        Command command = CommandContainer.getCommand(commandName);
        try {
            address = command.execute(req, resp);
            req.getRequestDispatcher(address).forward(req, resp);
        } catch (Exception ex) {
            req.setAttribute("ex", ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String address = "error.jsp";
        String commandName = req.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        try {
            address = command.execute(req, resp);
        } catch (Exception ex) {
            req.getSession().setAttribute("ex", ex);
        }
        resp.sendRedirect(address);
    }
}
