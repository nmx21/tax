package com.tax.logic;

import com.tax.Controller;
import com.tax.db.ConnectionPool;
import com.tax.exception.DBException;
import com.tax.db.entity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class AddressManager {
    private static AddressManager instance;
    private final ConnectionPool connectionPool;

    private static final Logger log = LoggerFactory.getLogger(AddressManager.class.getName());

    private AddressManager() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static synchronized AddressManager getInstance() {
        if (instance == null) {
            instance = new AddressManager();
        }
        return instance;
    }

    public Address findAddressById(int companyId) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAddressByCompanyId(con, companyId);
        } catch (SQLException ex) {
            log.error("Error in findAddressById  ", ex);
            throw new DBException("Cannot find address by id", ex);
        }
    }

    public void createAddress(Address address) throws DBException {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            con.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);
            connectionPool.createAddress(con, address);
            con.commit();
        } catch (SQLException ex) {
            log.error("Error in createAddress  ", ex);
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e) {
                    log.error("Error in createAddress rollback  ", ex);
                    e.printStackTrace();
                }
            }
            throw new DBException("Cannot create address ", ex);
        }
    }

    public List<Address> findAllAddress() throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAllAddress(con);
        } catch (SQLException ex) {
            log.error("Error in findAllAddress  ", ex);
            throw new DBException("Cannot find all addresses ", ex);
        }
    }
}
