package com.tax.logic;

import com.tax.db.ConnectionPool;
import com.tax.exception.DBException;
import com.tax.db.entity.Report;
import com.tax.db.entity.ReportStatus;
import com.tax.db.entity.ReportType;
import com.tax.db.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReportManager {
    private static ReportManager instance;
    private final ConnectionPool connectionPool;
    private static final Logger log = LoggerFactory.getLogger(ReportManager.class.getName());

    private ReportManager() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static synchronized ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }

    public ReportType findReportTypeById(int reportTypeId) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findReportTypeById(con, reportTypeId);
        } catch (SQLException ex) {
            log.error("Error in findReportTypeById  ", ex);
            throw new DBException("Cannot find report type", ex);
        }
    }

    public ReportStatus findReportStatusById(int reportStatusId) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findReportStatusById(con, reportStatusId);
        } catch (SQLException ex) {
            log.error("Error in findReportStatusById  ", ex);
            throw new DBException("Cannot find report type", ex);
        }
    }


    public void saveReport(User currentUser, Report report) throws DBException {
        Connection con;
        try {
            con = connectionPool.getConnection();
            con.setAutoCommit(false);
            List<Report> reportList = connectionPool.findAllReports(con);
            for (Report reportOne: reportList
                 ) {
                if (reportOne.equals(report)) return;
            }
            connectionPool.createReport(con, report, currentUser);
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            log.error("Error in saveReport  ", ex);
            throw new DBException("Cannot create companies", ex);
        }
    }

    public List<Report> findAllReportsByUser(User curentUser) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAllReportsByUser(con, curentUser);
        } catch (SQLException ex) {
            log.error("Error in findAllReportsByUser  ", ex);
            throw new DBException("Cannot find all reports by user", ex);
        }
    }

    public Report findReportById(int id) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findReportById(con, id);
        } catch (SQLException ex) {
            log.error("Error in findReportById  ", ex);
            throw new DBException("Cannot find report by id", ex);
        }
    }

    public List<Report> findReportByCompanyId(int id) throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findReportsByCompanyId(con, id);
        } catch (SQLException | DBException ex) {
            log.error("Error in findReportByCompanyId  ", ex);
            throw new DBException("Cannot find all reports by company id", ex);
        }
    }

    public int changeStatusReport(String operation, Integer reportId, String comment) throws DBException {
        int newStatus;
        switch (operation) {
            case ("accept"):
                newStatus = 1;
                break;
            case ("reject"):
                newStatus = 2;
                break;
            default:
                log.error("Error in changeStatusReport - new status not define  ");
                throw new IllegalArgumentException("Не визначено новий статус репорта");
        }
        int currentReportStatus = ReportManager.getInstance().findReportById(reportId).getReportStatus().getId();
        if (currentReportStatus == newStatus) return 0;
        Connection con;
        try {
            con = connectionPool.getConnection();
            connectionPool.updateReportStatus(con, reportId, newStatus, comment);

        } catch (SQLException ex) {
            log.error("Error in changeStatusReport  ", ex);
            throw new DBException("Cannot update status report", ex);
        }
        return 1;
    }

    public void updateReport(User currentUser, Report parseFile)  throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            connectionPool.updateReport(con, parseFile);
        } catch (SQLException ex) {
            log.error("Error in updateReport  ", ex);
            throw new DBException("Cannot update report", ex);
        }
    }


    public List<Report> findAllReports() throws DBException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAllReports(con);
        } catch (SQLException ex) {
            log.error("Error in findAllReports  ", ex);
            throw new DBException("Cannot find all users", ex);
        }
    }
}
