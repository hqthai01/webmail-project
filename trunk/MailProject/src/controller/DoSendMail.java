package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Attachment;
import model.Mail;
import model.MailBox;
import model.User;
import model.dao.AttachmentDAO;
import model.dao.MailDAO;
import model.dao.UserDAO;

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
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		String action = (String) request.getParameter("action");
		if (action != null) {
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
					String username = (String) session.getAttribute("username");
					String from = username + "@";
					from += (String) session.getAttribute("organization");
					String to = (String) request.getParameter("to");
					String subject = (String) request.getParameter("subject");
					String message = (String) request.getParameter("message");

					if (RegexUtil.isMailAddress(to)) {
						String to_username = to.substring(0, to.indexOf("@"));
						String to_organization = to.substring(to.indexOf("@") + 1, to.length());
						User usr = UserDAO.getUser(to_username);

						if (usr == null) {
							request.setAttribute("flag", "The destination mail is not exist");
							request.getRequestDispatcher("/compose_mail.jsp").forward(request, response);
							return;
						} else {
							if (!usr.getOrg().getOrgDomain().equals(to_organization)) {
								request.setAttribute("flag", "The destination mail is incorrect");
							} else {

								Mail mail_from = new Mail();
								mail_from.setMail_From(from);
								mail_from.setMail_To(to);
								mail_from.setSubject(subject);
								mail_from.setMessage(message);
								mail_from.setDate(new Date());
								mail_from.setFlag(Mail.FLAG_SENT);
								mail_from.setMailbox((MailBox) session.getAttribute("mailbox"));
								MailDAO.insert(mail_from);

								Mail mail_to = new Mail();
								mail_to.setMail_From(from);
								mail_to.setMail_To(to);
								mail_to.setSubject(subject);
								mail_to.setMessage(message);
								mail_to.setFlag(Mail.FLAG_UNREAD);
								mail_to.setDate(new Date());
								mail_to.setMailbox(usr.getMailBox());
								MailDAO.insert(mail_to);
								try {
									Iterator<FileItem> iter = items.iterator();
									String path = UPLOAD_DIRECTORY + "/" + (String) session.getAttribute("username");
									String to_path = UPLOAD_DIRECTORY + "/" + to_username;

									new File(path).mkdirs();
									new File(to_path).mkdirs();

									while (iter.hasNext()) {
										FileItem item = (FileItem) iter.next();

										if (!item.isFormField()) {

											String itemName = item.getName();
											if ((itemName == null) || itemName.equals("")) {
												continue;
											}
											String fileName = FilenameUtils.getName(itemName);
											File f_from = createNewname(new File(path + "/" + fileName));
											File f_to = createNewname(new File(to_path + "/" + fileName));

											Attachment att_from = new Attachment();
											att_from.setFileName(f_from.getName());
											att_from.setFilePath(f_from.getAbsolutePath());
											att_from.setMail(mail_from);
											AttachmentDAO.insert(att_from);

											Attachment att_to = new Attachment();
											att_to.setFileName(f_from.getName());
											att_to.setFilePath(f_from.getAbsolutePath());
											att_to.setMail(mail_to);
											AttachmentDAO.insert(att_to);

											try {
												item.write(f_from);
												item.write(f_to);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}
								} catch (Exception ex) {
									ex.printStackTrace();
								}

								session.setAttribute("mailbox", UserDAO.getUser(username).getMailBox());
								session.removeAttribute("items");
								request.setAttribute("flag", "Your mail is sent");
								request.getRequestDispatcher("/message.jsp").forward(request, response);
								return;
							}
						}
					} else {
						request.setAttribute("flag", "The address you want to sent to is incorrect");
					}

				}
			} else {
				if (action.equalsIgnoreCase("send")) {
					String username = (String) session.getAttribute("username");
					String from = username + "@";
					from += (String) session.getAttribute("organization");
					String to = (String) request.getParameter("to");
					String subject = (String) request.getParameter("subject");
					String message = (String) request.getParameter("message");
					System.out.println(RegexUtil.isMailAddress(to));
					if (RegexUtil.isMailAddress(to)) {
						String to_username = to.substring(0, to.indexOf("@"));
						String to_organization = to.substring(to.indexOf("@") + 1, to.length());
						User usr = UserDAO.getUser(to_username);

						if (usr == null) {
							request.setAttribute("flag", "The destination mail is not exist");
							request.getRequestDispatcher("/compose_mail.jsp").forward(request, response);
							return;
						} else {
							if (!usr.getOrg().getOrgDomain().equals(to_organization)) {
								request.setAttribute("flag", "The destination mail is incorrect");
							} else {

								Mail mail_from = new Mail();
								mail_from.setMail_From(from);
								mail_from.setMail_To(to);
								mail_from.setSubject(subject);
								mail_from.setMessage(message);
								mail_from.setDate(new Date());
								mail_from.setFlag(Mail.FLAG_SENT);
								mail_from.setMailbox((MailBox) session.getAttribute("mailbox"));
								MailDAO.insert(mail_from);

								Mail mail_to = new Mail();
								mail_to.setMail_From(from);
								mail_to.setMail_To(to);
								mail_to.setSubject(subject);
								mail_to.setMessage(message);
								mail_to.setFlag(Mail.FLAG_UNREAD);
								mail_to.setDate(new Date());
								mail_to.setMailbox(usr.getMailBox());
								MailDAO.insert(mail_to);

								session.setAttribute("mailbox", UserDAO.getUser(username).getMailBox());
								session.removeAttribute("items");
								request.setAttribute("flag", "Your mail is sent");
								request.getRequestDispatcher("/message.jsp").forward(request, response);
								return;
							}
						}
					} else {
						request.setAttribute("flag", "The address you want to sent to is incorrect");
					}
				}
			}
			request.getRequestDispatcher("/compose_mail.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/compose_mail.jsp").forward(request, response);
		}
	}

	private File createNewname(File file) {
		int counter = 1;
		String filename = file.getName().substring(0, file.getName().length() - 4);
		String extension = file.getName().substring(file.getName().length() - 4);
		while (file.exists()) {
			file = new File(file.getParent() + "/" + filename + (counter++) + extension);
		}
		return file;
	}

}
