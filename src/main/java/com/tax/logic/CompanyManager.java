package com.tax.logic;

import com.tax.db.ConnectionPool;
import com.tax.db.entity.Company;
import com.tax.db.entity.CompanyType;
import com.tax.db.entity.User;
import com.tax.exception.DBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CompanyManager {
    private static final Logger log = LoggerFactory.getLogger(CompanyManager.class.getName());
    private static CompanyManager instance;
    private final ConnectionPool connectionPool;

    private CompanyManager() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static synchronized CompanyManager getInstance() {
        if (instance == null) {
            instance = new CompanyManager();
        }
        return instance;
    }

    public Company findCompanyById(int companyId) throws DBException, SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findCompanyById(con, companyId);
        }
    }

    public void createCompanies(User user, Company company) throws SQLException {
        Connection con;
        con = connectionPool.getConnection();
        con.setTransactionIsolation(
                Connection.TRANSACTION_READ_COMMITTED);
        con.setAutoCommit(false);
        connectionPool.createCompany(con, company, user);
        con.commit();
    }

    public List<Company> findAllCompany() throws DBException, SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAllCompanies(con);
        }
    }

    public CompanyType findCompanyTypeById(int companyTypeId) throws SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findCompanyTypeById(con, companyTypeId);
        }
    }

    public List<Company> findCompanyByUserId(int id) throws DBException, SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findCompanyByUserId(con, id);
        }
    }
}
