package test;


import java.util.Date;

import model.Attachment;
import model.Certificate;
import model.Mail;
import model.MailBox;
import model.Organization;
import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @author hqthai01
 * Dec 29, 2014
 */
public class TestHibernate {
	public static void main(String args[]){
		AnnotationConfiguration config = new AnnotationConfiguration();
		//=============ADDING ANNOTATED CLASSES================
		config.addAnnotatedClass(Organization.class);
		config.addAnnotatedClass(Certificate.class);
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(MailBox.class);
		config.addAnnotatedClass(Mail.class);
		config.addAnnotatedClass(Attachment.class);
		
		//===================CONFIGURATION=====================
		config.configure("/props/hibernate.cfg.xml");
		new SchemaExport(config).create(true, true);
		
		//==============CREATE A SESSION TO WORK===============
		SessionFactory sfactory = config.buildSessionFactory();
		Session session = sfactory.getCurrentSession();
		session.beginTransaction();
		
		Certificate cert = new Certificate();
		cert.setFileName("hqthai.crt");
		cert.setFilePath("/home/hqthai01/hqthai.crt");
		
		Organization org = new Organization();
		org.setOrgDomain("hqthai01.wordpress.com");
		org.setOrgName("CA TEAM");
		org.setCertificate(cert);
		
		MailBox mailbox = new MailBox();
		
		Mail mail1 = new Mail();
		mail1.setMail_From("hqthai01@hotmail.com");
		mail1.setMail_To("annvcit@hotmail.com");
		mail1.setSubject("Test Hibernate");
		mail1.setMessage("Test Hibernate Annotation");
		mail1.setDate(new Date());
		mail1.setFlag(Mail.FLAG_UNREAD);
		mail1.setMailbox(mailbox);
		
		Attachment att1 = new Attachment();
		att1.setFileName("Test.crt");
		att1.setFilePath("/home/hqthai01/Desktop/Test.crt");
		att1.setMail(mail1);
		
		Attachment att2 = new Attachment();
		att2.setFileName("Test.crt");
		att2.setFilePath("/home/hqthai01/Desktop/Test.crt");
		att2.setMail(mail1);
		
		User usr = new User();
		usr.setAccount("hqthai01");
		usr.setPassword("holdholdme");
		usr.setCountry("VIET NAM");
		usr.setFullname("HUYNH QUANG THAI");
		usr.setPhone("01636 755 316");
		usr.setOrg(org);
		usr.setMailBox(mailbox);

		session.saveOrUpdate(usr);
		session.save(mailbox);
		session.saveOrUpdate(mail1);
		session.saveOrUpdate(att1);
		session.saveOrUpdate(att2);
		
		session.beginTransaction().commit();
	}
}
