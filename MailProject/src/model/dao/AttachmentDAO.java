package model.dao;

import model.Attachment;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class AttachmentDAO {
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

	public static Attachment getAttachment(int id) {
		try {
			Session session = getSession();
			session.beginTransaction();
			Attachment attachment = (Attachment) session.get(Attachment.class, id);
			session.beginTransaction().commit();
			return attachment;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(Attachment attachment) {
		process(attachment, "insert");
	}

	public static void update(Attachment attachment) {
		process(attachment, "update");
	}

	public static void delete(Attachment attachment) {
		process(attachment, "delete");
	}

	private static void process(Attachment attachment, String mode) {
		try {
			Session session = getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(attachment);
				break;
			case "update":
				session.update(attachment);
				break;
			case "delete":
				session.delete(attachment);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
