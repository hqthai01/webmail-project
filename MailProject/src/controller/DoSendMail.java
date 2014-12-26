package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoSendMail
 */
public class DoSendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoSendMail() {
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
		if (action != null) {

			switch (action.toLowerCase()) {
			case "attach file":
				request.getRequestDispatcher("/compose_mail.jsp").forward(request, response);
				break;
			case "remove selected":
				request.getRequestDispatcher("/compose_mail.jsp").forward(request, response);
				break;
			case "send":
				request.getRequestDispatcher("/compose_mail.jsp").forward(request, response);
				break;
			default:
				request.getRequestDispatcher("/compose_mail.jsp").forward(request, response);
				break;
			}

		} else {
			request.getRequestDispatcher("/compose_mail.jsp").forward(request, response);
		}
	}
}
