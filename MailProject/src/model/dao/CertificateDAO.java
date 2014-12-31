package model.dao;

import model.Certificate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * @author hqthai01 Dec 31, 2014
 */
public class CertificateDAO {
	private static SessionFactory getSessionFactory() {
		try {
			AnnotationConfiguration config = new AnnotationConfiguration();
			config.configure("/props/hibernate.cfg.xml");
			return config.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Session getSession() {
		try {
			return getSessionFactory().getCurrentSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Certificate getCertificate(int id) {
		try {
			Session session = getSession();
			session.beginTransaction();
			Certificate certificate = (Certificate) session.get(Certificate.class, id);
			session.beginTransaction().commit();
			return certificate;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(Certificate certificate) {
		process(certificate, "insert");
	}

	public static void update(Certificate certificate) {
		process(certificate, "update");
	}

	public static void delete(Certificate certificate) {
		process(certificate, "delete");
	}

	private static void process(Certificate certificate, String mode) {
		try {
			Session session = getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(certificate);
				break;
			case "update":
				session.update(certificate);
				break;
			case "delete":
				session.delete(certificate);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
