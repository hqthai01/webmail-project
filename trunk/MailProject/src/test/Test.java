package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import util.HibernateUtil;
import model.Organization;



public class Test {
	public static void main(String args[]) {
		Organization org = new Organization();
		org.setOrgDomain("thaibitch.vn");
		org.setOrgName("thaiasshole");
		
		
		Session ss = HibernateUtil.getSessionFactory().getCurrentSession();
		ss.beginTransaction();
		ss.save(org);
		ss.getTransaction().commit();
		
		
	}
}
