package controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		if (pos != null && checkNumber(pos)) {
			request.setAttribute("pos", pos);
			request.getRequestDispatcher("/read_mail.jsp").forward(request, response);
		}
	}

	private boolean checkNumber(String number) {
		try {
			String regex = "\\d+";
			Pattern p = Pattern.compile(regex);
			Matcher matcher = p.matcher(number);
			return matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
