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

    public Company findCompanyById(int companyId) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findCompanyById(con, companyId);
        } catch (SQLException ex) {
            log.error("Error in findCompanyById  ", ex);
            throw new DBException("Cannot find company", ex);
        }
    }

    public void createCompanies(User user, Company company) throws DBException {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            con.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);
            connectionPool.createCompany(con, company, user);
            con.commit();
        } catch (SQLException ex) {
            log.error("Error in createCompanies  ", ex);
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e) {
                    log.error("Error in createCompanies rollback  ", ex);
                    e.printStackTrace();
                }
            }
            throw new DBException("Cannot create companies", ex);
        }
    }

    public List<Company> findAllCompany() throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAllCompanies(con);
        } catch (SQLException ex) {
            log.error("Error in findAllCompany ", ex);
            throw new DBException("Cannot find all companies", ex);
        }
    }

    public CompanyType findCompanyTypeById(int companyTypeId) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findCompanyTypeById(con, companyTypeId);
        } catch (SQLException ex) {
            log.error("Error in findCompanyTypeById ", ex);
            throw new DBException("Cannot find company type", ex);
        }
    }

    public Object findCompanyByUserId(int id) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findCompanyByUserId(con, id);
        } catch (SQLException ex) {
            log.error("Error in findCompanyByUserId ", ex);
            throw new DBException("Cannot find company by user id", ex);
        }
    }
}
