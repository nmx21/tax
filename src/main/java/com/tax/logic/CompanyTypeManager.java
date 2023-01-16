package com.tax.logic;

import com.tax.db.ConnectionPool;
import com.tax.exception.DBException;
import com.tax.db.entity.CompanyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CompanyTypeManager {
    private static CompanyTypeManager instance;
    private final ConnectionPool connectionPool;
    private static final Logger log = LoggerFactory.getLogger(CompanyTypeManager.class.getName());

    private CompanyTypeManager() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static synchronized CompanyTypeManager getInstance() {
        if (instance == null) {
            instance = new CompanyTypeManager();
        }
        return instance;
    }

    public CompanyType findCompanyTypeByName(String type) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findCompanyTypeByName(con, type);
        } catch (SQLException ex) {
            log.error("Error in findCompanyTypeByName  ", ex);
            throw new DBException("Cannot find company type", ex);
        }
    }

    public CompanyType findCompanyTypeById(int id) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findCompanyTypeById(con, id);
        } catch (SQLException ex) {
            log.error("Error in findCompanyTypeById  ", ex);
            throw new DBException("Cannot find company type by id", ex);
        }
    }


    public List<CompanyType> findAllCompanyType() throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAllCompanyType(con);
        } catch (SQLException ex) {
            log.error("Error in findAllCompanyType  ", ex);
            throw new DBException("Cannot find all company type ", ex);
        }
    }
}
