package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;

/**
 * Servlet implementation class DoLogin
 */
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoLogin() {
		super();
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

		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String action = (String) request.getParameter("action");

		HttpSession session = request.getSession();
		if (action == null) {
			if (session.getAttribute("username") != null) {
				request.getRequestDispatcher("/inbox.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		} else if (action.equalsIgnoreCase("login")) {
			if (username == null || !checkLogin(username, password)) {
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} else {
				session.setAttribute("username", username);
				session.setAttribute("mailbox", UserDAO.getUser(username).getMailBox());
				request.getRequestDispatcher("/inbox.jsp").forward(request, response);
			}
		} 
	}

	private boolean checkLogin(String username, String password) {
		try {
			return UserDAO.checkUser(username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
