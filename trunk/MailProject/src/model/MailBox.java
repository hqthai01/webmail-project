package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MailBox implements Serializable {

	private static final long serialVersionUID = 0xAF1L;

	private int mailboxId;
	private List<Mail> mails;
	
	public MailBox(){
		mails = new ArrayList<Mail>();
	}

	@Id
	@GeneratedValue
	public int getMailboxId() {
		return mailboxId;
	}

	public void setMailboxId(int mailboxId) {
		this.mailboxId = mailboxId;
	}

	@OneToMany(targetEntity=Mail.class,mappedBy="mailbox",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<Mail> getMails() {
		return mails;
	}

	public void setMails(List<Mail> mails) {
		this.mails = mails;
	}
	
}
