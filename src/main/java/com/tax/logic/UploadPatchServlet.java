package com.tax.logic;

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
import java.util.UUID;


@MultipartConfig
@WebServlet("/patch")
public class UploadPatchServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UploadPatchServlet.class.getName());

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
            File file = new File(pathToFile);
            String typeFile = getFileExtension(file);
            switch (typeFile) {
                case "xml":
                    XMLRead xmlRead = new XMLRead();
                    try {
                        log.info(ReportManager.getInstance().updateReport(xmlRead.parseFile(pathToFile)) ? "The report has been updated" : "The report has not been updated");
                    } catch (SQLException | ParseException e) {
                        throw new RuntimeException(e);
                    }
                    deleteFile(file);
                    break;
                case "json":
                    JSONRead jsonRead = new JSONRead();
                    try {
                        log.info(ReportManager.getInstance().updateReport(jsonRead.parseFile(pathToFile)) ? "The report has been updated" : "The report has not been updated");
                    } catch (SQLException | ParseException e) {
                        throw new RuntimeException(e);
                    }
                    deleteFile(file);
                    break;
                default:
                    deleteFile(file);
                    log.error("Error in doPost - file type is not valid ");
                    throw new ParseFileException("Error in file type");
            }
        } finally {
            resp.sendRedirect("user_report_list_show.jsp");
        }
    }
}