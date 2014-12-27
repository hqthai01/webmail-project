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
import org.apache.commons.io.FilenameUtils;

import util.PropertyLoader;
import util.RegexUtil;

/**
 * Servlet implementation class DoSendMail
 */
public class DoSendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = PropertyLoader.getProperty("attachment");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	private void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = (String) request.getParameter("action");
		if (action != null) {

			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			List<FileItem> items = (List<FileItem>) session.getAttribute("items");
			if (items != null) {
				switch (action.toLowerCase()) {
				case "remove selected":
					String[] listIndex = request.getParameterValues("attachment");
					if (listIndex.length > 0) {
						for (int i = listIndex.length - 1; i >= 0; i--) {
							if (RegexUtil.isNumber(listIndex[i])) {
								items.remove(Integer.parseInt(listIndex[i]));
							}
						}
					}
					session.setAttribute("items", items);
					break;
				case "send":
					Iterator<FileItem> iter = items.iterator();
					String path = UPLOAD_DIRECTORY + "/" + (String) session.getAttribute("username");
					while (iter.hasNext()) {
						FileItem item = (FileItem) iter.next();

						if (!item.isFormField()) {

							String itemName = item.getName();
							if ((itemName == null) || itemName.equals("")) {
								continue;
							}
							String fileName = FilenameUtils.getName(itemName);
							File f = createNewname(new File(path + "/" + fileName));
							try {
								item.write(f);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}

					session.removeAttribute("items");
					request.setAttribute("flag", "Your mail is sent");
					request.getRequestDispatcher("/message.jsp").forward(request, response);
					return;
				}
			}
			request.getRequestDispatcher("/compose_mail.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/compose_mail.jsp").forward(request, response);
		}
	}

	private File createNewname(File file) {
		int counter = 1;
		while (file.exists()) {
			String filename = file.getName().substring(0, file.getName().length() - 4);
			String extension = file.getName().substring(file.getName().length() - 4);
			file = new File(file.getParent() + "/" + filename + (counter++) + extension);
		}
		return file;
	}

}
