package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.OrganizationDAOMock;
import net.cateam.service.verify.WebserviceClient;
import util.PropertyLoader;
import util.TerminalUtils;

/**
 * Servlet implementation class DoRegisterOrg
 */
public class DoRegisterOrg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String orgDomain = (String) request.getParameter("orgdomain");
		String orgName = (String) request.getParameter("orgname");
		String orgCert = (String) session.getAttribute("filename");

		String orgPath = PropertyLoader.getProperty("certificate");
		// downloadCertificate(request, response, orgPath);
		request.setAttribute("orgdomain", orgDomain);
		request.setAttribute("orgname", orgName);

		System.out.println(orgDomain);
		System.out.println(orgName);
		System.out.println(orgCert);

		if (isNull(orgDomain, orgName)) {
			request.setAttribute("flag", "fields cannot be empty");
		} else {
			if (OrganizationDAOMock.isExistOrg(orgDomain)) {

			} else {
				// verify certificate
				String command = "mkdir -p " + (orgPath + "/" + orgDomain);
				System.out.println(command);
				TerminalUtils.doCommand(command);

				command = "cp " + (orgPath + "/test/" + orgCert) + " " + orgPath + "/" + orgDomain + "/" + orgCert;
				System.out.println(command);
				TerminalUtils.doCommand(command);
				if (WebserviceClient.verify(orgPath + "/" + orgDomain + "/" + orgCert)) {
					// create new Organization
					// create Certificate
					// insert organization to database
					// insert certificate to database
					request.setAttribute("flag", "Your certificate is ok");
					session.removeAttribute("filename");
					TerminalUtils.removeFile(orgPath + "/test/"+orgCert);
				} else {
					TerminalUtils.removeFile(orgPath + "/" + orgDomain);
					TerminalUtils.removeFile(orgPath + "/test/" + orgCert);
					request.setAttribute("flag", "Your certificate is fake, please try others");
				}
			}
		}
		request.getRequestDispatcher("/org_regis.jsp").forward(request, response);

	}

	private boolean isNull(String... values) {
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null || values[i] == "")
				return true;
		}
		return false;
	}

}
