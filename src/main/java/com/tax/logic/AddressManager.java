package com.tax.logic;

import com.tax.db.ConnectionPool;
import com.tax.db.entity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddressManager {
    private static final Logger log = LoggerFactory.getLogger(AddressManager.class.getName());
    private static AddressManager instance;
    private final ConnectionPool connectionPool;

    private AddressManager() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static synchronized AddressManager getInstance() {
        if (instance == null) {
            instance = new AddressManager();
        }
        return instance;
    }

    public Address findAddressById(int companyId) throws SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAddressByCompanyId(con, companyId);
        }
    }

    public void createAddress(Address address) throws SQLException {
        Connection con;
        con = connectionPool.getConnection();
        con.setTransactionIsolation(
                Connection.TRANSACTION_READ_COMMITTED);
        con.setAutoCommit(false);
        connectionPool.createAddress(con, address);
        con.commit();
    }

    public List<Address> findAllAddress() throws SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAllAddress(con);
        }
    }
}
