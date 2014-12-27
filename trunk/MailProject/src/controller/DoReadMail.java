package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		if(session.getAttribute("username") == null){
			request.getRequestDispatcher("/index.jsp").forward(request, response);;
		}else if (pos != null && RegexUtil.isNumber(pos)) {
			request.setAttribute("pos", pos);
			request.getRequestDispatcher("/read_mail.jsp").forward(request, response);
		}
	}

	

}
