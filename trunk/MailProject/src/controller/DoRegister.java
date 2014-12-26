package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MailBox;
import model.Organization;
import model.User;
import model.dao.MailBoxDAOMock;
import model.dao.OrganizationDAOMock;
import model.dao.UserDAOMock;

/**
 * Servlet implementation class DoRegister
 */
public class DoRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoRegister() {
	}

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

		if (action == null) {
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		} else {
			String account = (String) request.getParameter("account");
			String password = (String) request.getParameter("password");
			String confirm = (String) request.getParameter("confirm");
			String fullname = (String) request.getParameter("fullname");
			String country = (String) request.getParameter("country");
			String phone = (String) request.getParameter("phone");
			String org = (String) request.getParameter("org");

			if (isNull(account, password, confirm, fullname, country, phone, org)) {
				request.setAttribute("flag", "The fields cannot be empty");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			} else if (UserDAOMock.isExist(account)) {
				request.setAttribute("flag", "Your username is exist");
				
				request.setAttribute("account", account);
				request.setAttribute("fullname", fullname);
				request.setAttribute("country", country);
				request.setAttribute("phone", phone);
				
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			} else if (!isSamePass(password, confirm)) {
				request.setAttribute("account", account);
				request.setAttribute("fullname", fullname);
				request.setAttribute("country", country);
				request.setAttribute("phone", phone);
				
				request.setAttribute("flag", "Your password confirmation is invalid");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			} else {
				Organization organization = OrganizationDAOMock.getOrganization(org);
				if (organization != null) {
					User user = new User();
					user.setAccount(account);
					user.setPassword(password);
					user.setCountry(country);
					user.setFullname(fullname);
					user.setOrg(organization);
					user.setPhone(phone);

					MailBox mailbox = new MailBox();
					mailbox.setUser(user);

					user.setMailBox(mailbox);
					
					if(UserDAOMock.createNewUser(user) != -1){
						if(MailBoxDAOMock.createNewMailBox(mailbox) != -1){
							request.setAttribute("flag", "YOUR ACCOUNT HAS BEEN CREATED");
							request.getRequestDispatcher("/regis_success.jsp").forward(request, response);
						}
					}
				} else {
					request.setAttribute("flag", "HACK DETECTED: your Organization is invalid");
					request.getRequestDispatcher("/register.jsp").forward(request, response);
				}
			}
		}
	}

	private boolean isNull(String... values) {
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null || values[i] == "") {
				return true;
			}
		}
		return false;
	}

	private boolean isSamePass(String password, String confirm) {
		return password.equals(confirm);
	}
}
