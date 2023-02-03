package com.tax.logic;

import com.tax.db.ConnectionPool;
import com.tax.exception.DBException;
import com.tax.db.entity.*;
import com.tax.exception.ParseFileException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReportManagerTest {

    @Test
    @Order(1)
    void findReportTypeById() throws DBException, SQLException {
        try(MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)){
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            when(connectionPool.getConnection()).thenReturn(Mockito.mock(Connection.class));
            when(connectionPool.findReportTypeById(any(), anyInt())).thenReturn(new ReportType());
            ReportManager reportManager = ReportManager.getInstance();
            ReportType company = reportManager.findReportTypeById(1);
            assertNotNull(company);

            when(connectionPool.findReportTypeById(any(), anyInt())).thenThrow(SQLException.class);
            Exception exception = assertThrows(DBException.class, () ->
                    reportManager.findReportTypeById(1));
            assertEquals("Cannot find report type", exception.getMessage());
        }
    }

    @Test
    @Order(2)
    void saveReport() throws SQLException, DBException, ParseFileException {
        try(MockedStatic<ConnectionPool> mocked = mockStatic(ConnectionPool.class)){
            ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
            Connection con = Mockito.mock(Connection.class);
            mocked.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            when(connectionPool.getConnection()).thenReturn(con);
            User user = new User();
            Report report = new Report();
            doNothing().when(connectionPool).createReport(any(), any(Report.class), any(User.class));
            ReportManager reportManager = ReportManager.getInstance();
            reportManager.saveReport(user, report);
        }
    }
    @Test
    @Order(3)
    void saveReportVerify() throws DBException, ParseFileException, SQLException {
        ReportManager reportManager = Mockito.mock(ReportManager.class);
        User user = new User();
        Report report = new Report();
        doNothing().when(reportManager).saveReport(user, report);
        reportManager.saveReport(user, report);
        verify(reportManager, times(1)).saveReport(user, report);
    }
}