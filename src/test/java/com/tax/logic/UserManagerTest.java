package com.tax.logic;

import com.tax.db.ConnectionPool;
import com.tax.exception.DBException;
import com.tax.db.entity.Report;
import com.tax.db.entity.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserManagerTest {

    @Test
    @Order(1)
    void findUser() throws DBException, SQLException {
        try(MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)){
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            User user = new User("user1", "userPassword","user@user.com");
            when(connectionPool.findUser(any(), anyString())).thenReturn(user);
            when(connectionPool.getConnection()).thenReturn(Mockito.mock(Connection.class));
            when(connectionPool.findUserById(any(), anyInt())).thenReturn(user);
            UserManager userManager = UserManager.getInstance();
            User user1 = userManager.findUser("user1");
            assertNotNull(user1);
            assertEquals("user1", user1.getUsername());
            assertEquals("userPassword", user1.getPassword());
            assertEquals("user@user.com", user1.getEmail());
            when(connectionPool.findUser(any(), anyString())).thenThrow(SQLException.class);
            Exception exception = assertThrows(DBException.class, () ->
                    userManager.findUser("user1"));
            assertEquals("Cannot find user", exception.getMessage());
        }
    }

    @Test
    @Order(2)
    void createUsers() {
    }

    @Test
    @Order(3)
    void findAllUsers() throws SQLException, DBException {
        try(MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)){
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            when(connectionPool.findAllUsers(any())).thenReturn(new ArrayList<>());
            when(connectionPool.getConnection()).thenReturn(Mockito.mock(Connection.class));
            UserManager userManager = UserManager.getInstance();
            List<User> users = userManager.findAllUsers();
            assertNotNull(users);
        }
    }

    @Test
    @Order(4)
    void updateUser() {
    }

    @Test
    @Order(5)
    void findUserById() throws SQLException, DBException {
        try(MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)){
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            User user = new User("user1", "userPassword","user@user.com");
            when(connectionPool.findUserById(any(), anyInt())).thenReturn(user);
            when(connectionPool.getConnection()).thenReturn(Mockito.mock(Connection.class));
            UserManager userManager = UserManager.getInstance();
            User user1 = userManager.findUserById(1);
            assertNotNull(user1);
            assertEquals("user1", user1.getUsername());
            assertEquals("userPassword", user1.getPassword());
            assertEquals("user@user.com", user1.getEmail());
        }
    }

    @Test
    @Order(6)
    void findAllReports() throws SQLException, DBException {
        try(MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)){
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            when(connectionPool.findAllReports(any())).thenReturn(new ArrayList<>());
            when(connectionPool.getConnection()).thenReturn(Mockito.mock(Connection.class));
            ReportManager reportManager = ReportManager.getInstance();
            List<Report> reports = reportManager.findAllReports();
            assertNotNull(reports);
        }
    }
}