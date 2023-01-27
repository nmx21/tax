package com.tax.db;

import com.tax.db.entity.*;
import com.tax.exception.DBException;
import com.tax.logic.AddressManager;
import com.tax.logic.CompanyManager;
import com.tax.logic.ReportManager;
import com.tax.logic.UserManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ConnectionPool {

    private static final String FIND_USER_BY_LOGIN = "select * from user where username=?";
    private static final String FIND_USER_BY_ID = "select * from user where id=?";
    private static final String INSERT_USER = "insert into user (username, password, email, role_id) values ( ?, ?, ?, 0)";
    private static final String FIND_ALL_USERS = "select * from user";
    private static final String FIND_ALL_REPORTS = "select * from report";
    private static final String UPDATE_EMAIL_FOR_USER = "update user set email = ? where id = ?";
    private static final String UPDATE_PASS_FOR_USER = "update user set password = ? where id = ?";
    private static final String INSERT_ADDRESS = "insert into address (country, state, city, street, building, building_letter, office, office_letter) values (?,?,?,?,?,?,?,?) ";
    private static final String FIND_ALL_ADDRESS = "select * from address where ";
    private static final String FIND_ADDRESS_BY_ID = "" + "select * from address where id = ?";
    private static final String FIND_ADDRESS = "select * from address";
    private static final String FIND_COMPANY_TYPE_BY_NAME = "select * from company_type where type = ?";
    private static final String FIND_COMPANY_TYPE_BY_ID = "select * from company_type where id = ?";
    private static final String FIND_ALL_COMPANY_TYPE = "select * from company_type";
    private static final String INSERT_COMPANY = "insert into company_data (company_type_id, name, inn_edrpou, address_id) values (?,?,?,?)";
    private static final String FIND_COMPANY_BY_NAME = "select company_data.id, company_type.id as type_id, company_type.type, company_data.name, company_data.inn_edrpou, company_data.address_id from company_data, company_type where company_data.company_type_id = company_type.id and company_type.id = ? and name = ?";
    private static final String FIND_ALL_COMPANY_BY_USER_ID = "select company_data.id, company_type.id as type_id, company_type.type, company_data.name, company_data.inn_edrpou, company_data.address_id from company_data, company_type where company_data.company_type_id = company_type.id and company_data.id in (select company_data_id from user_has_company_data where user_id = ?)";
    private static final String FIND_COMPANY_BY_ID = "select company_data.id, company_type.id as type_id, company_type.type, company_data.name, company_data.inn_edrpou, company_data.address_id from company_data, company_type where company_data.company_type_id = company_type.id and company_data.id = ?";
    private static final String FIND_ALL_COMPANIES = "select `company_data`.`id`, `company_data`.`company_type_id` as `type_id`, `company_type`.`type`,`company_data`.`name`, `company_data`.`inn_edrpou`, company_data.address_id FROM `company_data`,`company_type` where `company_data`.`company_type_id` = `company_type`.`id`";
    private static final String UPDATE_USER_COMPANY_TABLE = "insert into user_has_company_data (user_id, company_data_id) values (?,?) ";
    private static final String UPDATE_DATA_COMPANY_ADDRESS_TABLE = "insert into company_data_has_address (company_data_id, address_id) values ( ?, ?)";
    private static final String FIND_REPORT_TYPE_BY_ID = "select * FROM report_type where id= ?";
    private static final String FIND_REPORT_STATUS_BY_ID = "select * FROM report_status where id= ?";
    private static final String INSERT_REPORT = "insert into report (company_data_id, report_type_id, financial_income, tax_amount, description, user_id, report_body, status_id) values (? , ?,  ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_REPORT = "update report set company_data_id = ?, report_type_id = ?, financial_income = ?, tax_amount = ?, description = ?, status_id = ? where id = ?";
    private static final String FIND_ALL_REPORTS_BY_USER = "select * from report where user_id = ? ";
    private static final String FIND_REPORT_BY_ID = "select * from report where id = ?";
    private static final String FIND_ALL_REPORTS_BY_COMPANY_ID = "select * from report where company_data_id = ?";
    private static final String UPDATE_REPORT_STATUS = "update report set status_id = ?, comment = ? where id = ?";
    private static ConnectionPool instance;
    private final DataSource ds;

    private ConnectionPool() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env/");
            ds = (DataSource) envContext.lookup("jdbc/tax_service");
        } catch (NamingException ex) {
            throw new IllegalStateException("Cannot obtain a data source", ex);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private static User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setRoleId(rs.getInt("role_id"));
        user.setDateRegistry(rs.getString("create_time"));
        return user;
    }

    private static Address extractAddress(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setId(rs.getInt("id"));
        address.setCountry(rs.getString("country"));
        address.setRegion(rs.getString("state"));
        address.setCity(rs.getString("city"));
        address.setStreet(rs.getString("street"));
        address.setBuilding(rs.getString("building"));
        address.setBuildingLetter(rs.getString("building_letter"));
        address.setOffice(rs.getString("office"));
        address.setOfficeLetter(rs.getString("office_letter"));
        return address;
    }

    private static CompanyType extractCompanyType(ResultSet rs) throws SQLException {
        CompanyType companyType = new CompanyType();
        companyType.setId(rs.getInt("id"));
        companyType.setType(rs.getString("type"));
        return companyType;
    }

    public Connection getConnection() throws SQLException {
        // adjust a connection
        return ds.getConnection();
    }

    public User findUser(Connection con, String login) throws SQLException {
        User user = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
        }
        return user;
    }

    public void createUser(Connection con, User user) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            preparedStatement.setString(k++, user.getUsername());
            preparedStatement.setString(k++, user.getPassword());
            preparedStatement.setString(k, user.getEmail());

            if (preparedStatement.executeUpdate() > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    int userId = rs.getInt(1);
                    user.setId(userId);
                }
            }
        }
    }

    public List<User> findAllUsers(Connection con) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(FIND_ALL_USERS)) {
            while (rs.next()) {
                users.add(extractUser(rs));
            }
        }
        return users;
    }

    public boolean updateUserEmail(Connection con, String newValue, int id) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_EMAIL_FOR_USER, Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            preparedStatement.setString(k++, newValue);
            preparedStatement.setInt(k, id);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean updateUserPassword(Connection con, String newValue, int id) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_PASS_FOR_USER, Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            preparedStatement.setString(k++, newValue);
            preparedStatement.setInt(k, id);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        }
        return false;
    }

    public Integer createAddress(Connection con, Address address) throws SQLException {
        Address addressInBase = findAddress(con, address);
        if (addressInBase == null) {
            try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS)) {
                int k = 1;
                preparedStatement.setString(k++, address.getCountry());
                preparedStatement.setString(k++, address.getRegion());
                preparedStatement.setString(k++, address.getCity());
                preparedStatement.setString(k++, address.getStreet());
                preparedStatement.setInt(k++, Integer.parseInt(address.getBuilding()));
                preparedStatement.setString(k++, address.getBuildingLetter());
                preparedStatement.setInt(k++, Integer.parseInt(address.getOffice()));
                preparedStatement.setString(k, address.getOfficeLetter());
                if (preparedStatement.executeUpdate() > 0) {
                    ResultSet rs = preparedStatement.getGeneratedKeys();
                    if (rs.next()) {
                        int addressId = rs.getInt(1);
                        address.setId(addressId);
                    }
                }
            }
            return address.getId();
        }
        return addressInBase.getId();
    }

    public List<Address> findAllAddress(Connection con) throws SQLException {
        List<Address> addresses = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(FIND_ALL_ADDRESS)) {
            while (rs.next()) {
                addresses.add(extractAddress(rs));
            }
        }
        return addresses;
    }

    public Address findAddress(Connection con, Address address) throws SQLException {
        Address returnAddress = null;
        int k = 0;
        ArrayList<String> findKey = new ArrayList<>();
        ArrayList<String> findValue = new ArrayList<>();
        ArrayList<String> field = new ArrayList<>();

        if (emptyOrNot(address.getCountry())) {
            ++k;
            field.add("?");
            findKey.add("country = ? ");
            findValue.add(address.getCountry());
        }
        if (emptyOrNot(address.getRegion())) {
            ++k;
            field.add("?");
            findKey.add("state = ? ");
            findValue.add(address.getRegion());
        }
        if (emptyOrNot(address.getCity())) {
            ++k;
            field.add("?");
            findKey.add("city = ? ");
            findValue.add(address.getCity());
        }
        if (emptyOrNot(address.getStreet())) {
            ++k;
            field.add("?");
            findKey.add("street = ? ");
            findValue.add(address.getStreet());
        }
        if (emptyOrNot(address.getBuilding())) {
            ++k;
            field.add("?");
            findKey.add("building = ? ");
            findValue.add(address.getBuilding());
        }
        if (emptyOrNot(address.getBuildingLetter())) {
            ++k;
            field.add("?");
            findKey.add("building_letter = ? ");
            findValue.add(address.getBuildingLetter());
        }
        if (emptyOrNot(address.getOffice())) {
            ++k;
            field.add("?");
            findKey.add("office = ? ");
            findValue.add(address.getOffice());
        }
        if (emptyOrNot(address.getOfficeLetter())) {
            ++k;
            field.add("?");
            findKey.add("office_letter = ? ");
            findValue.add(address.getOfficeLetter());
        }

        if (k > 0) {
            ResultSet rs;
            try (PreparedStatement preparedStatement = con.prepareStatement(FIND_ADDRESS + " where " + String.join(" and ", findKey) + " limit 1")) {
                for (int i = 1; i <= k; i++) {
                    preparedStatement.setString(i, findValue.get(i - 1));
                }
                rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    returnAddress = extractAddress(rs);
                }
            }
        }
        return returnAddress;
    }

    private boolean emptyOrNot(String value) {
        return value != null && !value.isEmpty();
    }

    private void updateUserCompanyTable(Connection con, Company company, User user) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_USER_COMPANY_TABLE)) {
            int k = 1;
            preparedStatement.setInt(k++, user.getId());
            preparedStatement.setInt(k, company.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void createCompany(Connection con, Company company, User user) throws SQLException {
        int addressId = createAddress(con, company.getAddress());
        if (findCompany(con, company) != null) {
            return;
        }
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_COMPANY, Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            preparedStatement.setInt(k++, company.getType().getId());
            preparedStatement.setString(k++, company.getName());
            preparedStatement.setString(k++, company.getInn());
            preparedStatement.setInt(k, addressId);

            if (preparedStatement.executeUpdate() > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    int companyId = rs.getInt(1);
                    company.setId(companyId);
                    updateUserCompanyTable(con, company, user);
                }
            }
        }
    }

    private void updateDataAddressTable(Connection con, CompanyDataAddress companyDataAddress) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_DATA_COMPANY_ADDRESS_TABLE)) {
            int k = 1;
            preparedStatement.setInt(k++, companyDataAddress.getCompanyDataId());
            preparedStatement.setInt(k, companyDataAddress.getAddressId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Company findCompany(Connection con, Company company) throws SQLException {
        Company companyData = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_COMPANY_BY_NAME)) {
            preparedStatement.setString(1, String.valueOf(company.getType().getId()));
            preparedStatement.setString(2, company.getName());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                companyData = extractCompany(rs);
            }
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        return companyData;
    }

    public Company findCompanyById(Connection con, int company) throws SQLException, DBException {
        Company companyData = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_COMPANY_BY_ID)) {
            preparedStatement.setInt(1, company);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                companyData = extractCompany(rs);
            }
        }
        return companyData;
    }

    private Company extractCompany(ResultSet rs) throws SQLException, DBException {
        CompanyType companyType = new CompanyType();
        companyType.setId(rs.getInt("type_id"));
        companyType.setType(rs.getString("type"));

        Company company = new Company();
        company.setId(rs.getInt("id"));
        company.setType(companyType);
        company.setName(rs.getString("name"));
        company.setInn(rs.getString("inn_edrpou"));

        Address address = new Address();
        address.setId(rs.getInt("address_id"));
        address = AddressManager.getInstance().findAddressById(address.getId());
        company.setAddress(address);
        return company;
    }

    public CompanyType findCompanyTypeByName(Connection con, String type) throws SQLException {
        CompanyType companyType = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_COMPANY_TYPE_BY_NAME)) {
            preparedStatement.setString(1, type);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                companyType = extractCompanyType(rs);
            }
        }
        return companyType;
    }

    public CompanyType findCompanyTypeById(Connection con, int id) throws SQLException {
        CompanyType companyType = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_COMPANY_TYPE_BY_ID)) {

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                companyType = extractCompanyType(rs);
            }
        }
        return companyType;
    }

    public List<CompanyType> findAllCompanyType(Connection con) throws SQLException {
        List<CompanyType> companyType = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_ALL_COMPANY_TYPE); ResultSet rs = preparedStatement.executeQuery()) {
            if (rs.next()) {
                companyType.add(extractCompanyType(rs));
            }
        }
        return companyType;
    }

    public List<Company> findAllCompanies(Connection con) throws SQLException, DBException {
        List<Company> companies = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(FIND_ALL_COMPANIES)) {
            while (rs.next()) {
                companies.add(extractCompany(rs));
            }
        }
        return companies;
    }

    public Address findAddressByCompanyId(Connection con, int addressId) throws SQLException {
        Address address = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_ADDRESS_BY_ID)) {
            preparedStatement.setInt(1, addressId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                address = extractAddress(rs);
            }
        }
        return address;
    }

    public User findUserById(Connection con, int id) throws SQLException {
        User user = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
        }
        return user;
    }

    public List<Report> findAllReports(Connection con) throws SQLException, DBException {
        List<Report> reports = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(FIND_ALL_REPORTS)) {
            while (rs.next()) {
                reports.add(extractReport(rs));
            }
        }
        return reports;
    }

    private Report extractReport(ResultSet rs) throws SQLException, DBException {
        ReportType reportType = new ReportType();
        reportType.setId(rs.getInt("report_type_id"));
        reportType = ReportManager.getInstance().findReportTypeById(reportType.getId());

        ReportStatus reportStatus = new ReportStatus();
        reportStatus.setId(rs.getInt("status_id"));
        reportStatus = ReportManager.getInstance().findReportStatusById(reportStatus.getId());

        Report report = new Report();
        report.setId(rs.getInt("id"));
        report.setReportType(reportType);
        report.setFinancialIncome(rs.getInt("financial_income"));
        report.setTaxAmount(rs.getInt("tax_amount"));
        report.setDescription(rs.getString("description"));
        report.setComment(rs.getString("comment"));
        report.setDateCreate(rs.getDate("date_create"));
        report.setDateLastEdit(rs.getDate("date_last_edit"));
        report.setReportStatus(reportStatus);
        report.setCompany(CompanyManager.getInstance().findCompanyById(rs.getInt("company_data_id")));
        report.setUser(UserManager.getInstance().findUserById(rs.getInt("user_id")));
        report.setReportBody(rs.getString("report_body"));
        return report;
    }

    public ReportType findReportTypeById(Connection con, int reportTypeId) throws SQLException, DBException {
        ReportType reportType = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_REPORT_TYPE_BY_ID)) {
            preparedStatement.setInt(1, reportTypeId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                reportType = extractReportType(rs);
            }
        }
        return reportType;
    }

    private ReportType extractReportType(ResultSet rs) throws SQLException, DBException {
        ReportType reportType = new ReportType();
        reportType.setId(rs.getInt("id"));
        reportType.setCompanyTypeId(CompanyManager.getInstance().findCompanyTypeById(rs.getInt("company_type_id")));
        reportType.setType(rs.getString("report_type"));
        return reportType;
    }

    public void createReport(Connection con, Report report, User currentUser) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_REPORT, Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            preparedStatement.setInt(k++, report.getCompany().getId());
            preparedStatement.setInt(k++, report.getReportType().getId());
            preparedStatement.setInt(k++, report.getFinancialIncome());
            preparedStatement.setInt(k++, report.getTaxAmount());
            preparedStatement.setString(k++, report.getDescription());
            preparedStatement.setInt(k++, currentUser.getId());
            preparedStatement.setString(k++, "report Body");
            preparedStatement.setInt(k, 0);
            if (preparedStatement.executeUpdate() > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    int reportId = rs.getInt(1);
                    report.setId(reportId);
                }
            }
        }
    }

    public List<Report> findAllReportsByUser(Connection con, User curentUser) throws SQLException, DBException {
        List<Report> reports = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_ALL_REPORTS_BY_USER)) {
            preparedStatement.setInt(1, curentUser.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                reports.add(extractReport(rs));
            }
        }
        return reports;
    }

    public ReportStatus findReportStatusById(Connection con, int reportStatusId) throws SQLException {
        ReportStatus reportStatus = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_REPORT_STATUS_BY_ID)) {
            preparedStatement.setInt(1, reportStatusId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                reportStatus = extractReportStatus(rs);
            }
        }
        return reportStatus;
    }

    private ReportStatus extractReportStatus(ResultSet rs) throws SQLException {
        ReportStatus reportStatus = new ReportStatus();
        reportStatus.setId(rs.getInt("id"));
        reportStatus.setType(rs.getString("name"));
        return reportStatus;
    }

    public Report findReportById(Connection con, int id) throws SQLException, DBException {
        Report report = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_REPORT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                report = extractReport(rs);
            }
        }
        return report;
    }

    public List<Report> findReportsByCompanyId(Connection con, int id) throws SQLException, DBException {
        List<Report> reports = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_ALL_REPORTS_BY_COMPANY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                reports.add(extractReport(rs));
            }
        }
        return reports;
    }

    public void updateReportStatus(Connection con, Integer reportId, Integer newStatus, String comment) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_REPORT_STATUS, Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            preparedStatement.setString(k++, String.valueOf(newStatus));
            preparedStatement.setString(k++, comment);
            preparedStatement.setInt(k, reportId);
            if (preparedStatement.executeUpdate() > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
            }
        }
    }

    public void updateReport(Connection con, Report parseFile) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_REPORT, Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            preparedStatement.setInt(k++, parseFile.getCompany().getId());
            preparedStatement.setInt(k++, parseFile.getReportType().getId());
            preparedStatement.setInt(k++, parseFile.getFinancialIncome());
            preparedStatement.setInt(k++, parseFile.getTaxAmount());
            preparedStatement.setString(k++, parseFile.getDescription());
            preparedStatement.setInt(k++, 3);
            preparedStatement.setInt(k, parseFile.getId());
            if (preparedStatement.executeUpdate() > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    int reportId = rs.getInt(1);
                    parseFile.setId(reportId);
                }
            }
        }
    }

    public List<Company> findCompanyByUserId(Connection con, int id) throws DBException, SQLException {
        List<Company> companies = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_ALL_COMPANY_BY_USER_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                companies.add(extractCompany(rs));
            }
        }
        return companies;
    }
}
