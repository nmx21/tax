package com.tax.logic;

import com.tax.db.ConnectionPool;
import com.tax.db.entity.User;
import com.tax.exception.DBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserManager {
    private static final Logger log = LoggerFactory.getLogger(UserManager.class.getName());
    private static UserManager instance;
    private final ConnectionPool connectionPool;


    private UserManager() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User findUser(String username) throws SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findUser(con, username);
        }
    }

    public void createUsers(User user) throws DBException, SQLException {
        Connection con;
        con = connectionPool.getConnection();
        con.setTransactionIsolation(
                Connection.TRANSACTION_READ_COMMITTED);
        con.setAutoCommit(false);
        if (findUser(user.getUsername()) == null) {
            connectionPool.createUser(con, user);
        }
        con.commit();
    }

    public List<User> findAllUsers() throws SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAllUsers(con);
        }
    }

    public void updateUser(User user) throws SQLException {
        Connection con = null;
        boolean changeEmail = false;
        boolean changePassword = false;
        con = connectionPool.getConnection();
        con.setTransactionIsolation(
                Connection.TRANSACTION_READ_COMMITTED);
        con.setAutoCommit(false);

        if (user.getEmail() != null) {
            if (!user.getEmail().equals("")) {
                changeEmail = connectionPool.updateUserEmail(con, user.getEmail(), user.getId());
            }
        }
        if (user.getPassword() != null) {
            if (!user.getPassword().equals("")) {
                changePassword = connectionPool.updateUserPassword(con, user.getPassword(), user.getId());
            }
        }
        con.commit();
        if (!changeEmail || !changePassword) log.warn("Email or Password not changed");
    }

    public User findUserById(int id) throws SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findUserById(con, id);
        }
    }

    public void addLoginTime(User user) throws DBException, SQLException {
        Connection con = null;
        con = connectionPool.getConnection();
        con.setTransactionIsolation(
                Connection.TRANSACTION_READ_COMMITTED);
        con.setAutoCommit(false);
        connectionPool.addLoginTime(con, user);
        con.commit();
    }
}
