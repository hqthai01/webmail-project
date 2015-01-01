package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Attachment;
import model.Mail;
import model.dao.AttachmentDAO;
import util.RegexUtil;

/**
 * Servlet implementation class DoDownload
 */
public class DoDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	
		String action = (String)request.getParameter("action");
		if(action != null){
			HttpSession session = request.getSession();
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "attachment;filename=attachment.zip");
			
			ZipOutputStream zout = new ZipOutputStream(response.getOutputStream());
			switch(action.toLowerCase()){
			case "download all":
				Mail mail = (Mail)session.getAttribute("mail");
				Iterator<Attachment> iter = mail.getAttachments().iterator();
				
				while(iter.hasNext()){
					addFile(zout, new File(iter.next().getFilePath()));
				}
				zout.flush();
				zout.close();
				break;
			case "download selected":
				String[] values = request.getParameterValues("attach");
				if(values != null && values.length>0){
					for(int i = 0 ; i <values.length; i++){
						String id = values[i];
						if(RegexUtil.isNumber(id)){
							addFile(zout, new File(AttachmentDAO.getAttachment(Integer.parseInt(id)).getFilePath()));
						}
					}
					zout.flush();
					zout.close();
				}
				break;
			}
		}
		request.getRequestDispatcher("");
	}

	private void addFile(ZipOutputStream zout, File file) {
		try {
			FileInputStream in = new FileInputStream(file);
			zout.putNextEntry(new ZipEntry(file.getName()));

			int length;
			byte[] buff = new byte[4096];

			while ((length = in.read(buff)) != -1) {
				zout.write(buff, 0, length);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
