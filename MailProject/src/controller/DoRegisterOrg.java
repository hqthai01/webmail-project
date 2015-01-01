package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Certificate;
import model.Organization;
import model.dao.OrganizationDAO;
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
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String orgDomain = (String) request.getParameter("orgdomain");
		String orgName = (String) request.getParameter("orgname");
		String orgCert = (String) session.getAttribute("filename");

		String orgPath = PropertyLoader.getProperty("certificate");
		session.setAttribute("orgdomain", orgDomain);
		session.setAttribute("orgname", orgName);

		System.out.println(orgDomain);
		System.out.println(orgName);
		System.out.println(orgCert);

		if (isNull(orgDomain, orgName)) {
			session.setAttribute("flag", "fields cannot be empty");
		} else {
			if (OrganizationDAO.isExistOrg(orgDomain)) {
				session.setAttribute("flag", "Organization is exist");
			} else {
				// verify certificate
				String command = "mkdir -p " + (orgPath + "/" + orgDomain);
				System.out.println(command);
				TerminalUtils.doCommand(command);

				String newName = orgDomain+".crt";
				command = "cp " + (orgPath + "/test/" + orgCert) + " " + orgPath + "/" + orgDomain + "/" + newName;
				System.out.println(command);
				TerminalUtils.doCommand(command);
				if (WebserviceClient.verify(orgPath + "/" + orgDomain + "/" + newName)) {
					File file = new File(orgPath +"/" +orgDomain +"/"+newName);
					Certificate certificate = new Certificate();
					certificate.setFileName(file.getName());
					certificate.setFilePath(file.getAbsolutePath());
					
					Organization org = new Organization();
					org.setOrgDomain(orgDomain);
					org.setOrgName(orgName);
					org.setCertificate(certificate);

					OrganizationDAO.insert(org);
					
					session.setAttribute("flag", "Your certificate is ok your domain is registered");
					session.removeAttribute("filename");
					TerminalUtils.removeFile(orgPath + "/test/" + orgCert);
				} else {
					TerminalUtils.removeFile(orgPath + "/" + orgDomain);
					TerminalUtils.removeFile(orgPath + "/test/" + orgCert);
					session.setAttribute("flag", "Your certificate is fake, please try others");
				}
			}
		}
		request.getRequestDispatcher("/forward_page.jsp").forward(request, response);

	}

	private boolean isNull(String... values) {
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null || values[i] == "")
				return true;
		}
		return false;
	}

}
