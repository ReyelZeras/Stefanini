package br.com.stefanini.vulnerabilidades.controller.copy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "FileUploadServlet", urlPatterns = { "/fileuploadservlet" })
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class FileUploadServlet extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  System.out.println("Cheguei aqui no FileUploadServlet"); 
    /* Receive file uploaded to the Servlet from the HTML5 form */
    Part filePart = request.getPart("file");
    String fileName = filePart.getSubmittedFileName();
    for (Part part : request.getParts()) {
      part.write("C:\\upload\\" + fileName);
    }
    response.getWriter().print("The file uploaded sucessfully.");
  }

}
