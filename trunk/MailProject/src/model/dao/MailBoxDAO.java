package model.dao;

import model.MailBox;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class MailBoxDAO {

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

	public static MailBox getMailBox(int id) {
		try {
			Session session = getSession();
			session.beginTransaction();
			MailBox mailbox = (MailBox) session.get(MailBox.class, id);
			session.beginTransaction().commit();
			return mailbox;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(MailBox mailbox) {
		process(mailbox, "insert");
	}

	public static void update(MailBox mailbox) {
		process(mailbox, "update");
	}

	public static void delete(MailBox mailbox) {
		process(mailbox, "delete");
	}

	private static void process(MailBox mailbox, String mode) {
		try {
			Session session = getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(mailbox);
				break;
			case "update":
				session.update(mailbox);
				break;
			case "delete":
				session.delete(mailbox);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
