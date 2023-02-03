package com.tax.logic;

import com.tax.db.ConnectionPool;
import com.tax.db.entity.Report;
import com.tax.db.entity.ReportStatus;
import com.tax.db.entity.ReportType;
import com.tax.db.entity.User;
import com.tax.exception.DBException;
import com.tax.exception.ParseFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReportManager {
    private static final Logger log = LoggerFactory.getLogger(ReportManager.class.getName());
    private static ReportManager instance;
    private final ConnectionPool connectionPool;

    private ReportManager() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static synchronized ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }

    public ReportType findReportTypeById(int reportTypeId) throws SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findReportTypeById(con, reportTypeId);
        }
    }

    public ReportStatus findReportStatusById(int reportStatusId) throws DBException, SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findReportStatusById(con, reportStatusId);
        }
    }


    public void saveReport(User currentUser, Report report) throws DBException, SQLException, ParseFileException {
        Connection con;
        con = connectionPool.getConnection();
        con.setAutoCommit(false);
        List<Report> reportList = connectionPool.findAllReports(con);
        for (Report reportOne : reportList
        ) {
            if (reportOne.equals(report)) return;
        }
        connectionPool.createReport(con, report, currentUser);
        con.commit();
        con.setAutoCommit(true);
    }

    public List<Report> findAllReportsByUser(User curentUser) throws DBException, SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAllReportsByUser(con, curentUser);
        }
    }

    public Report findReportById(int id) throws DBException, SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findReportById(con, id);
        }
    }

    public List<Report> findReportByCompanyId(int id) throws DBException, SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findReportsByCompanyId(con, id);
        }
    }

    public int changeStatusReport(String operation, Integer reportId, String comment) throws DBException, SQLException {
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
        con = connectionPool.getConnection();
        connectionPool.updateReportStatus(con, reportId, newStatus, comment);
        return 1;
    }

    public void updateReport(Report parseFile) throws SQLException {
        try (Connection con = connectionPool.getConnection()) {
            connectionPool.updateReport(con, parseFile);
        }
    }


    public List<Report> findAllReports() throws DBException, SQLException {
        try (Connection con = connectionPool.getConnection()) {
            return connectionPool.findAllReports(con);
        }
    }
}
