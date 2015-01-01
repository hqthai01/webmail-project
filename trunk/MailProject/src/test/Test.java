package test;

import model.Certificate;
import model.dao.CertificateDAO;

public class Test {
	public static void main(String args[]) {
		Certificate c = CertificateDAO.getCertificate(1);
		c.setFileName("hqthai01.wordpress.com.crt");
		c.setFilePath("/home/hqthai01/Desktop/upload/certificates/hqthai01.wordpress.com/hqthai01.wordpress.com.crt");

		CertificateDAO.update(c);
	}
}
