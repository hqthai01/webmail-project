package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class MailBox implements Serializable {

	private static final long serialVersionUID = 0xAF1L;

	private int mailboxId;
	private User user;
	private List<Mail> listMail;

	{
		listMail = new ArrayList<Mail>();
	}
	
	@Override
	public String toString() {
		return "MailBox [user=" + user + ", Number of Mails=" + listMail.size() + "]";
	}

	@Id
	@GeneratedValue
	@Column(name = "mailBox_PK")
	public int getMailboxId() {
		return mailboxId;
	}

	public void setMailboxId(int mailboxId) {
		this.mailboxId = mailboxId;
	}

	@OneToOne(mappedBy = "mailBox", cascade = CascadeType.ALL)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(targetEntity = Mail.class, mappedBy = "mailbox", cascade = CascadeType.ALL)
	public List<Mail> getListMail() {
		return listMail;
	}

	public void setListMail(List<Mail> listMail) {
		this.listMail = listMail;
	}
	
	public void addMail(Mail mail){
		this.listMail.add(mail);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
