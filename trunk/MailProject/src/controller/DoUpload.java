package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class DoUpload
 */
public class DoUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			FileItemFactory itemsFactory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(itemsFactory);
			List<FileItem> items = null;

			try {
				items = upload.parseRequest(request);

				HttpSession session = request.getSession();
				session.removeAttribute("items");
				session.setAttribute("items", items);

				request.getRequestDispatcher("/DoComposeMail").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
