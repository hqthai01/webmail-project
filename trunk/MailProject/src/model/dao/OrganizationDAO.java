package model.dao;

import java.util.List;

import model.Organization;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class OrganizationDAO {
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

	public static Organization getOrganization(String orgDomain) {
		try {
			Session session = getSession();
			session.beginTransaction();
			Organization organization = (Organization) session.get(Organization.class, orgDomain);
			session.beginTransaction().commit();
			return organization;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void insert(Organization organization) {
		process(organization, "insert");
	}

	public static void update(Organization organization) {
		process(organization, "update");
	}

	public static void delete(Organization organization) {
		process(organization, "delete");
	}

	private static void process(Organization organization, String mode) {
		try {
			Session session = getSession();
			session.beginTransaction();

			switch (mode) {
			case "insert":
				session.save(organization);
				break;
			case "update":
				session.update(organization);
				break;
			case "delete":
				session.delete(organization);
				break;
			}

			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isExistOrg(String orgDomain){
		return getOrganization(orgDomain) !=null;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Organization> getOrganizations(){
		try {
			Session session = getSession();
			session.beginTransaction();
			
			return session.createQuery("FROM Organization").list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
