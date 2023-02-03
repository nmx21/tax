package com.tax.logic;

import com.tax.db.ConnectionPool;
import com.tax.db.entity.Address;
import com.tax.db.entity.Company;
import com.tax.db.entity.CompanyType;
import com.tax.db.entity.User;
import com.tax.exception.DBException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyManagerTest {

    @Test
    @Order(1)
    void findCompanyById() throws DBException, SQLException {
        try (MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)) {
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            when(connectionPool.findCompanyById(any(), anyInt())).thenReturn(new Company());
            when(connectionPool.getConnection()).thenReturn(Mockito.mock(Connection.class));
            when(connectionPool.findCompanyTypeById(any(), anyInt())).thenReturn(new CompanyType());
            CompanyManager companyManager = CompanyManager.getInstance();
            Company company = companyManager.findCompanyById(1);
            assertNotNull(company);

            when(connectionPool.findCompanyById(any(), anyInt())).thenThrow(SQLException.class);
            Exception exception = assertThrows(DBException.class, () ->
                    companyManager.findCompanyById(1));
            assertEquals("Cannot find company", exception.getMessage());
        }
    }

    @Test
    @Order(2)
    void createCompanies() throws SQLException, DBException {
        try (MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)) {
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            Connection con = Mockito.mock(Connection.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            when(connectionPool.getConnection()).thenReturn(con);
            User user = new User();
            user.setId(1);
            user.setUsername("test");
            user.setPassword("test");
            user.setEmail("test@test.com");
            user.setRoleId(1);
            user.setDateRegistry("12.12.2022");
            Company company = new Company();
            company.setId(1);
            company.setInn("12345678");
            company.setName("TEST");
            CompanyType companyType = new CompanyType();
            companyType.setId(1);
            companyType.setType("test company");
            company.setType(companyType);
            Address address = new Address();
            address.setCountry("test");
            address.setCity("test");
            address.setBuilding("12");
            address.setOffice("7");
            address.setRegion("test");
            address.setStreet("test");
            address.setBuildingLetter("A");
            company.setAddress(address);
            doNothing().when(connectionPool).createCompany(any(), any(Company.class), any(User.class));
            CompanyManager companyManager = CompanyManager.getInstance();
            companyManager.createCompanies(user, company);
        }
    }

    @Test
    @Order(3)
    void findCompanyTypeById() throws DBException, SQLException {
        try (MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)) {
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            when(connectionPool.findCompanyTypeById(any(), anyInt())).thenReturn(new CompanyType());
            when(connectionPool.getConnection()).thenReturn(Mockito.mock(Connection.class));
            CompanyManager companyManager = CompanyManager.getInstance();
            CompanyType companyType = companyManager.findCompanyTypeById(1);
            assertNotNull(companyType);
        }
    }

    @Test
    @Order(4)
    void findAllCompany() throws DBException, SQLException {
        try (MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)) {
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            when(connectionPool.findAllCompanies(any())).thenReturn(new ArrayList<>());
            when(connectionPool.getConnection()).thenReturn(Mockito.mock(Connection.class));
            CompanyManager companyManager = CompanyManager.getInstance();
            List<Company> companies = companyManager.findAllCompany();
            assertNotNull(companies);

        }
    }

    @Test
    @Order(5)
    void createCompanyVerify() throws DBException, SQLException {
        CompanyManager companyManager = Mockito.mock(CompanyManager.class);
        User user = new User();
        Company company = new Company();
        doNothing().when(companyManager).createCompanies(user, company);
        companyManager.createCompanies(user, company);
        verify(companyManager, times(1)).createCompanies(user, company);
    }

}