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

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.info("Upload execute, time= {}",  LocalDateTime.now());
        try {
            Part filePart = req.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            InputStream is = filePart.getInputStream();
            String imagesAddress = getServletContext().getRealPath("/report_files");
            String pathToFile = imagesAddress + File.separator + UUID.randomUUID() + fileName;
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
                    ReportManager.getInstance().saveReport(currentUser, xmlRead.parseFile(pathToFile));
                    file.delete();
                    break;
                case "json":
                    JSONRead jsonRead = new JSONRead();
                    ReportManager.getInstance().saveReport(currentUser, jsonRead.parseFile(pathToFile));
                    file.delete();
                    break;
                default:
                    file.delete();
                    log.error("Error in doPost - file type is not valid ");
                    throw new ParseFileException("Error parse file");
            }
        } catch (ParseException e) {
            log.error("Error (ParseException) in doPost  ", e);
            throw new ParseFileException("Error (ParseException) parce file", e);
        } catch (DBException | SQLException e) {
            log.error("Error (DBException) in doPost  ", e);
            throw new ParseFileException("Error (DBException) parce file", e);
        }
        resp.sendRedirect("user_report_list_show.jsp");

    }
}