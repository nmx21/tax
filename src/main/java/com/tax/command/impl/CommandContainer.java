package com.tax.command.impl;

import com.tax.command.Command;
import com.tax.command.impl.admin.*;
import com.tax.command.impl.all.*;
import com.tax.command.impl.user.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandContainer {
    private static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("registrationForm", new RegistrationFormCommand());
        commands.put("createUsers", new CreateUsersCommand());
        commands.put("userReportList", new UserReportListCommand());
        commands.put("userCompanyList", new UserCompanyListCommand());
        commands.put("addCompany", new AddCompanyCommand());
        commands.put("userProfile", new UserProfileCommand());
        commands.put("adminProfile", new AdminProfileCommand());
        commands.put("editProfile", new EditProfileCommand());
        commands.put("companyList", new ListCompanyCommand());
        commands.put("showCompanyInfo", new ShowCompanyInfo());
        commands.put("userShowCompanyInfo", new UserShowCompanyInfo());
        commands.put("showUserInfo", new ShowUserInfo());
        commands.put("adminListReports", new ListReportCommand());
        commands.put("listUsers", new ListUsersCommand());
        commands.put("userReportDetails", new UserReportDetails());
        commands.put("adminReportDetails", new AdminReportDetails());
        commands.put("editReportStatus", new EditReportStatus());
    }

    private CommandContainer() {
    }

    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
