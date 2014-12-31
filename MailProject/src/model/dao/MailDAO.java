package model.dao;

import model.Mail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class MailDAO {
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

	public static Mail getMail(int id) {
		try {
			Session session = getSession();
			session.beginTransaction();
			Mail mail = (Mail) session.get(Mail.class, id);
			session.beginTransaction().commit();
			return mail;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(Mail mail) {
		process(mail, "insert");
	}

	public static void update(Mail mail) {
		process(mail, "update");
	}

	public static void delete(Mail mail) {
		process(mail, "delete");
	}

	private static void process(Mail mail, String mode) {
		try {
			Session session = getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(mail);
				break;
			case "update":
				session.update(mail);
				break;
			case "delete":
				session.delete(mail);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
