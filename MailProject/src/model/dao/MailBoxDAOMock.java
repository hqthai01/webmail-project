package model.dao;

import java.util.ArrayList;
import java.util.Collections;


import model.Mail;
import model.MailBox;
import model.User;

public class MailBoxDAOMock {
	public static MailBox getMailBox(String username) {
		if (username.equalsIgnoreCase("hqthai01")) {
			User usr = new User();
			usr.setAccount(username);
//			MailBox mailbox = new MailBox(usr);
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_UNREAD));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_UNREAD));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_UNREAD));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_UNREAD));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_UNREAD));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_UNREAD));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_UNREAD));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_UNREAD));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_UNREAD));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_UNREAD));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_UNREAD));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_UNREAD));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_UNREAD));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_UNREAD));
			
//			List<String> listAttach = new ArrayList<String>();
//			listAttach.add("Abc");
//			listAttach.add("Abc");
//			listAttach.add("Abc");
//			listAttach.add("Abc");
//			listAttach.add("Abc");
//			listAttach.add("Abc");
//			listAttach.add("Abc");
//			listAttach.add("Abc");
//
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", listAttach, Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", listAttach, Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", listAttach, Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", listAttach, Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_READ));
// 
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			mailbox.addMail(new Mail("abc@abc.com", "test@xyz.com", "Test Subject", "abc def ght", new ArrayList<String>(), Mail.FLAG_SENT));
//			
//			Collections.shuffle(mailbox.getListMail());
//			return mailbox;
			return null;
		}
		return null;
	}
}
