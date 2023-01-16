package com.my;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@MultipartConfig
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Part filePart = req.getPart("file");
		String fileName = filePart.getSubmittedFileName();
		
		InputStream is = filePart.getInputStream();

		String imagesAddress = getServletContext().getRealPath("/images");
		System.out.println(imagesAddress);
		
		Files.copy(is, 
				Paths.get(imagesAddress + "/" + fileName),
				StandardCopyOption.REPLACE_EXISTING);
		
		resp.sendRedirect("test.jsp");
		
	}

}