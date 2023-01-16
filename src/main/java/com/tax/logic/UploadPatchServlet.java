package com.tax.logic;

import com.tax.db.DBException;
import com.tax.db.entity.User;
import com.tax.logic.parser.JSONRead;
import com.tax.logic.parser.XMLRead;
import org.json.simple.parser.ParseException;

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
import java.util.UUID;


@MultipartConfig
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        InputStream is = filePart.getInputStream();
        String imagesAddress = getServletContext().getRealPath("/report_files");
        String pathToFile = imagesAddress + "/" + UUID.randomUUID().toString() + fileName;
        Files.copy(is,
                Paths.get(pathToFile),
                StandardCopyOption.REPLACE_EXISTING);
        req.getSession().setAttribute("message", "Файл завантажено");

        try {
            User currentUser = new User();
            currentUser = (User) req.getSession().getAttribute("currentUser");
            String typeFile = getFileExtension(new File(pathToFile));
            if ("xml".equals(typeFile)) {
                XMLRead xmlRead = new XMLRead();
                ReportManager.getInstance().saveReport(currentUser, xmlRead.parseFile(pathToFile));
            } else if ("json".equals(typeFile)) {
                JSONRead jsonRead = new JSONRead();
                ReportManager.getInstance().saveReport(currentUser, jsonRead.parseFile(pathToFile));
            } else {
                throw new RuntimeException("Incorrect file");
            }


        } catch (ParseException | DBException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("user_report_list_show.jsp");

    }

}