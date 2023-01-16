package com.tax.logic;

import com.tax.db.ConnectionPool;
import com.tax.db.DBException;
import com.tax.db.entity.Address;
import com.tax.db.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddreddManager {
    private static AddreddManager instance;

    public static synchronized AddreddManager getInstance() {
        if (instance == null) {
            instance = new AddreddManager();
        }
        return instance;
    }

    private AddreddManager() {
        connectionPool = ConnectionPool.getInstance();
    }

    private ConnectionPool connectionPool;

    public Address findAdress(String username) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAddress(con, username);
        } catch (SQLException ex) {
            // (1) write to log: log.error(..., ex);
            ex.printStackTrace();

            // (2) throw your own exception
            throw new DBException("Cannot find address", ex);
        }
    }

    public void createAddress(Address... addresses) throws DBException {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            con.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);

            for (Address address : addresses) {
                connectionPool.createAddress(con, address);
            }

            con.commit();
        } catch (SQLException ex) {
            // (1) write to log: log.error("Cannot create users", ex);
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
            throw new DBException("Cannot create address ", ex);
        }
    }

    public List<User> findAllUsers() throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAllUsers(con);
        } catch (SQLException ex) {
            // (1) write to log: log.error(..., ex);
            ex.printStackTrace();

            // (2) throw your own exception
            throw new DBException("Cannot find all addresses ", ex);
        }
    }
}
