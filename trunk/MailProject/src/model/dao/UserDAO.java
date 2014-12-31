package model.dao;

import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class UserDAO {

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

	public static User getUser(String account) {
		try {
			Session session = getSession();
			session.beginTransaction();
			User user= (User) session.get(User.class, account);
			session.beginTransaction().commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(User user) {
		process(user, "insert");
	}

	public static void update(User user) {
		process(user, "update");
	}

	public static void delete(User user) {
		process(user, "delete");
	}

	private static void process(User user, String mode) {
		try {
			Session session = getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(user);
				break;
			case "update":
				session.update(user);
				break;
			case "delete":
				session.delete(user);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isExist(String account){
		return getUser(account) != null;
	}

	public static boolean checkUser(String account, String password){
		User usr = getUser(account);
		if(usr == null){
			return false;
		}
		return usr.getPassword().equals(password);
	}
	
}
