package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mail;
import model.MailBox;
import model.Organization;
import model.dao.MailBoxDAO;
import model.dao.MailDAO;
import model.dao.OrganizationDAO;
import net.cateam.service.verify.WebserviceClient;
import util.RegexUtil;

/**
 * Servlet implementation class DoReadMail
 */
public class DoReadMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoReadMail() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String pos = (String) request.getParameter("pos");
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);

		} else if (pos != null && RegexUtil.isNumber(pos)) {
			Mail mail = MailDAO.getMail(Integer.parseInt(pos));
			MailBox mb = (MailBox) session.getAttribute("mailbox");

			if (mail != null) {
				if (mb.getMailboxId() == mail.getMailbox().getMailboxId()) {
					session.setAttribute("mail", mail);

					if (!verifyCertificate(mail) && mail.getFlag() != Mail.FLAG_SENT) {
						request.setAttribute("flag", "Mail from untrusted organization");
					}

					if (mail.getFlag() == Mail.FLAG_UNREAD) {
						mail.setFlag(Mail.FLAG_READ);
						MailDAO.update(mail);
						session.setAttribute("mailbox", MailBoxDAO.getMailBox(mb.getMailboxId()));
					}
				}
			}

			request.getRequestDispatcher("/read_mail.jsp").forward(request, response);
		}
	}

	private boolean verifyCertificate(Mail mail) {
		String from = mail.getMail_From();
		String orgDomain = from.substring(from.indexOf("@") + 1, from.length());
		Organization org = OrganizationDAO.getOrganization(orgDomain);
		if (org == null) {
			return false;
		}

		return WebserviceClient.verify(org.getCertificate().getFilePath());
	}

}
