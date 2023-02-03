package com.tax.logic;

import com.tax.db.ConnectionPool;
import com.tax.db.entity.CompanyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CompanyTypeManager {
    private static final Logger log = LoggerFactory.getLogger(CompanyTypeManager.class.getName());
    private static CompanyTypeManager instance;
    private final ConnectionPool connectionPool;

    private CompanyTypeManager() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static synchronized CompanyTypeManager getInstance() {
        if (instance == null) {
            instance = new CompanyTypeManager();
        }
        return instance;
    }

    public CompanyType findCompanyTypeByName(String type) throws SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findCompanyTypeByName(con, type);
        }
    }

    public CompanyType findCompanyTypeById(int id) throws SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findCompanyTypeById(con, id);
        }
    }


    public List<CompanyType> findAllCompanyType() throws SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAllCompanyType(con);
        }
    }
}
