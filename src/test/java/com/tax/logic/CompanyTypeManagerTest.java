package com.tax.logic;

import com.tax.db.ConnectionPool;
import com.tax.exception.DBException;
import com.tax.db.entity.CompanyType;
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
class CompanyTypeManagerTest {

    @Test
    @Order(1)
    void findCompanyTypeByName() throws SQLException, DBException {

        try(MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)){
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            when(connectionPool.findCompanyTypeByName(any(), anyString())).thenReturn(new CompanyType());
            when(connectionPool.getConnection()).thenReturn(Mockito.mock(Connection.class));
            when(connectionPool.findCompanyTypeById(any(), anyInt())).thenReturn(new CompanyType());
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            CompanyTypeManager companyTypeManager = CompanyTypeManager.getInstance();
            CompanyType companyType = companyTypeManager.findCompanyTypeByName("type");
            assertNotNull(companyType);

            when(connectionPool.findCompanyTypeByName(any(), anyString())).thenThrow(SQLException.class);
            Exception exception = assertThrows(DBException.class, () ->
                    companyTypeManager.findCompanyTypeByName("type"));
            assertEquals("Cannot find company type", exception.getMessage());
        }
    }

    @Test
    @Order(2)
    void findCompanyTypeById() throws SQLException, DBException {
        try(MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)){
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            when(connectionPool.getConnection()).thenReturn(Mockito.mock(Connection.class));
            when(connectionPool.findCompanyTypeById(any(), anyInt())).thenReturn(new CompanyType());
            CompanyTypeManager companyTypeManager = CompanyTypeManager.getInstance();
            CompanyType companyType = companyTypeManager.findCompanyTypeById(1);
            assertNotNull(companyType);
        }
    }

    @Test
    @Order(3)
    void findAllCompanyType() throws SQLException, DBException {
        try(MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)){
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            when(connectionPool.getConnection()).thenReturn(Mockito.mock(Connection.class));
            when(connectionPool.findAllCompanyType(any())).thenReturn(new ArrayList<>());
            CompanyTypeManager companyTypeManager = CompanyTypeManager.getInstance();
            List<CompanyType> companyTypes = companyTypeManager.findAllCompanyType();
            assertNotNull(companyTypes);
        }
    }
}