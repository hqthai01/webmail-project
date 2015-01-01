package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;

/**
 * Servlet implementation class DoCheckMail
 */
public class DoCheckMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoCheckMail() {
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
		HttpSession session = request.getSession();
		session.removeAttribute("mail");
		session.setAttribute("mailbox", UserDAO.getUser((String)session.getAttribute("username")).getMailBox());
		
		request.getRequestDispatcher("/inbox.jsp").forward(request, response);
	}

}
