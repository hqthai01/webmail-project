package model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import model.Attachment;
import model.Mail;
import model.MailBox;
import model.User;

public class MailBoxDAOMock {

	public static MailBox getMailBox(String username) {
		Random rd = new Random();
		if (username.equalsIgnoreCase("hqthai01")) {
			User usr = new User();
			usr.setAccount(username);
			MailBox mailbox = new MailBox();
			mailbox.setUser(usr);
			
			Mail mail;
			for (int i = 0; i < 200; i++) {
				mail = new Mail();
				mail.setMailId(i);
				mail.setFrom("abc@abc.com");
				mail.setTo("test@xyz.com");
				mail.setSubject("Test subject");
				mail.setMessage("Example mail message body");
				mail.setDate(new Date());

				switch (rd.nextInt(3)) {
				case 0:
					mail.setFlag(Mail.FLAG_READ);
					break;
				case 1:
					mail.setFlag(Mail.FLAG_UNREAD);
					break;
				case 2:
					mail.setFlag(Mail.FLAG_SENT);
				}

				switch (rd.nextInt(2)) {
				case 0:
					mail.setListAttachment(AttachmentDAOMock.getAttachmentsOf(mail.getMailId()));
					break;
				case 1:
					mail.setListAttachment(new ArrayList<Attachment>());
					break;
				}
				mailbox.addMail(mail);
			}

			return mailbox;
		}
		return null;
	}
	
	public static int createNewMailBox(MailBox mailbox){
		return 1;
	}
}
