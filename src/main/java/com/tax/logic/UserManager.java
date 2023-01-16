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

    public User findUser(String username) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findUser(con, username);
        } catch (SQLException ex) {
            log.error("Error in findUser  ", ex);
            throw new DBException("Cannot find user", ex);
        }
    }

    public void createUsers(User user) throws DBException {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            con.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);
            if (findUser(user.getUsername()) == null) {
                connectionPool.createUser(con, user);
            }
            con.commit();
        } catch (SQLException ex) {
            log.error("Error in createUser  ", ex);
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e) {
                    log.error("Error in findUser rollback ", ex);
                }
            }
            log.error("Error in createUser  ", ex);
            throw new DBException("Cannot create users", ex);
        }
    }

    public List<User> findAllUsers() throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAllUsers(con);
        } catch (SQLException ex) {
            log.error("Error in findAllUsers  ", ex);
            throw new DBException("Cannot find all users", ex);
        }
    }

    public void updateUser(User user) throws DBException {
        Connection con = null;
        try {
            boolean changeEmail = false;
            boolean changePassword = false;
            con = connectionPool.getConnection();
            con.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);
            if (!user.getEmail().equals("")) {
                changeEmail = connectionPool.updateUserEmail(con, user.getEmail(), user.getId());
            }
            if (!user.getPassword().equals(""))
                changePassword = connectionPool.updateUserPassword(con, user.getPassword(), user.getId());
            con.commit();
            if (!changeEmail && !changePassword) log.warn("Email or Password not changed");
        } catch (SQLException ex) {
            log.error("Error in updateUser  ", ex);
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e) {
                    log.error("Error in updateUser rollback  ", ex);
                    e.printStackTrace();
                }
            }
            throw new DBException("Cannot update users", ex);
        }
    }

    public User findUserById(int id) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findUserById(con, id);
        } catch (SQLException ex) {
            log.error("Error in findUserById  ", ex);
            throw new DBException("Cannot find user", ex);
        }
    }

}
