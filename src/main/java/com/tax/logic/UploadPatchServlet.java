package com.tax.logic;

import com.tax.exception.DBException;
import com.tax.exception.ParseFileException;
import com.tax.db.entity.User;
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

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        try {
            User currentUser = (User) req.getSession().getAttribute("currentUser");
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
            if ("xml".equals(typeFile)) {
                XMLRead xmlRead = new XMLRead();
                System.out.println("========== " + xmlRead);
                ReportManager.getInstance().updateReport(currentUser, xmlRead.parseFile(pathToFile));
                file.delete();
            } else if ("json".equals(typeFile)) {
                JSONRead jsonRead = new JSONRead();
                ReportManager.getInstance().updateReport(currentUser, jsonRead.parseFile(pathToFile));
                file.delete();
            } else {
                file.delete();
                log.error("Error in doPost - file type is not valid ");
                throw new ParseFileException("Error in file type");
            }
        } catch (IOException | ParseException | DBException e) {
            log.error("Error in doPost:  ", e);
            throw new ParseFileException("Error parce file", e);
        }
        resp.sendRedirect("user_report_list_show.jsp");
    }
}