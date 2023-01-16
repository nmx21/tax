package com.tax.logic;

import com.tax.db.ConnectionPool;
import com.tax.db.DBException;
import com.tax.db.entity.Company;
import com.tax.db.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CompanyManager {
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

    public Company findCompanyById(int company_id) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findCompanyById(con,company_id);
        } catch (SQLException ex) {
            // (1) write to log: log.error(..., ex);
            ex.printStackTrace();

            // (2) throw your own exception
            throw new DBException("Cannot find company", ex);
        }
    }

    public void createCompanies(User user, Company... companies) throws DBException {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            con.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);

            for (Company company : companies) {
                connectionPool.createCompany(con, company, user);

            }

            con.commit();
        } catch (SQLException ex) {
            // (1) write to log: log.error("Cannot create companies", ex);
            ex.printStackTrace();

            // (2)
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e) {
                    // write to log
                    e.printStackTrace();
                }
            }

            // (3) throw your own exception
            throw new DBException("Cannot create companies", ex);
        }
    }

    public List<Company> findAllCompany() throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAllCompanies(con);
        } catch (SQLException ex) {
            // (1) write to log: log.error(..., ex);
            ex.printStackTrace();

            // (2) throw your own exception
            throw new DBException("Cannot find all companies", ex);
        }
    }

}
