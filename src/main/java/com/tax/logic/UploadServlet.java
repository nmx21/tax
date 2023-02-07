package com.tax.logic;

import com.tax.db.entity.User;
import com.tax.exception.DBException;
import com.tax.exception.ParseFileException;
import com.tax.logic.parser.impl.JSONRead;
import com.tax.logic.parser.impl.XMLRead;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;


@MultipartConfig
@WebServlet("/update")
public class UploadServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UploadServlet.class.getName());

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    private static void deleteFile(File file) {
        if (file.delete()) {
            log.info("File deleted");
        } else {
            log.error("File not delete");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.info("Upload execute, time= {}", LocalDateTime.now());
        try {
            Part filePart = req.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            InputStream is = filePart.getInputStream();
            String fileAddress = getServletContext().getRealPath("/report_files");
            String pathToFile = fileAddress + File.separator + UUID.randomUUID() + fileName;
            Files.copy(is,
                    Paths.get(pathToFile),
                    StandardCopyOption.REPLACE_EXISTING);
            req.getSession().setAttribute("message", "Файл завантажено");

            User currentUser = (User) req.getSession().getAttribute("currentUser");
            File file = new File(pathToFile);
            String typeFile = getFileExtension(file);
            switch (typeFile) {
                case "xml":
                    XMLRead xmlRead = new XMLRead();
                    try {
                        log.info(ReportManager.getInstance().saveReport(currentUser, xmlRead.parseFile(pathToFile)) ? "The report has been add" : "The report has not been add");
                    } catch (SQLException | ParseException | DBException e) {
                        throw new RuntimeException(e);
                    }
                    deleteFile(file);
                    break;
                case "json":
                    JSONRead jsonRead = new JSONRead();
                    try {
                        log.info(ReportManager.getInstance().saveReport(currentUser, jsonRead.parseFile(pathToFile)) ? "The report has been add" : "The report has not been add");
                    } catch (SQLException | ParseException | DBException e) {
                        throw new RuntimeException(e);
                    }
                    deleteFile(file);
                    break;
                default:
                    deleteFile(file);
                    log.error("Error in doPost - file type is not valid ");
                    throw new ParseFileException("Error parse file - file type is not valid");
            }
        } finally {
            resp.sendRedirect("user_report_list_show.jsp");
        }

    }
}