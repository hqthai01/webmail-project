package controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
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
import org.apache.commons.io.FilenameUtils;

import util.PropertyLoader;

/**
 * Servlet implementation class DoVerify
 */
public class DoVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String orgPath = PropertyLoader.getProperty("certificate")+"/"+"test";
		downloadCertificate(request, response, orgPath);
		request.getRequestDispatcher("/org_regis.jsp").forward(request, response);
	}
	
	private File createNewname(File file) {
		int counter = 1;
		String filename = file.getName().substring(0, file.getName().length() - 4);
		String extension = file.getName().substring(file.getName().length() - 4);
		String parent = file.getParent();
		while (file.exists()) {
			file = new File(parent + "/" + filename + (counter++) + extension);
		}
		return file;
	}
	
	private void downloadCertificate(HttpServletRequest request, HttpServletResponse response, String orgPath){
		
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			FileItemFactory itemsFactory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(itemsFactory);
			List<FileItem> items = null;
			HttpSession session = request.getSession();
			
			try {
				items = upload.parseRequest(request);

				new File(orgPath).mkdirs();
				
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();

					if (!item.isFormField()) {

						String itemName = item.getName();
						if ((itemName == null) || itemName.equals("")) {
							continue;
						}
						String fileName = FilenameUtils.getName(itemName);
						File f = createNewname(new File(orgPath + "/a" + fileName));
						session.setAttribute("filename", f.getName());
						try {
							item.write(f);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
