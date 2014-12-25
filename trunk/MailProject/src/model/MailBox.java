package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class MailBox implements Serializable {

	private static final long serialVersionUID = 0xAF1L;

	private User user;
	private List<Mail> listMail;

	public MailBox(User user) {
		this.user = user;
		this.listMail = new ArrayList<Mail>();
	}

	public MailBox(User user, List<Mail> listMail) {
		this.user = user;
		this.listMail = listMail;
	}

	public void addMail(Mail mail){
		listMail.add(mail);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Mail> getListMail() {
		return listMail;
	}

	public void setListMail(List<Mail> listMail) {
		this.listMail = listMail;
	}

	@Override
	public String toString() {
		return "MailBox [user=" + user + ", Number of Mails=" + listMail.size() + "]";
	}
	
	
	
}
