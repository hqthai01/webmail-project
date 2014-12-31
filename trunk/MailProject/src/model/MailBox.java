package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class MailBox implements Serializable {

	private static final long serialVersionUID = 0xAF1L;

	private int mailboxId;
	private Set<Mail> mails;
	
	public MailBox(){
		mails = new HashSet<Mail>();
	}

	@Id
	@GeneratedValue
	public int getMailboxId() {
		return mailboxId;
	}

	public void setMailboxId(int mailboxId) {
		this.mailboxId = mailboxId;
	}

	@OneToMany(targetEntity=Mail.class,mappedBy="mailbox",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@OrderBy("date ASC")
	public Set<Mail> getMails() {
		return mails;
	}

	public void setMails(Set<Mail> mails) {
		this.mails = mails;
	}

	@Override
	public String toString() {
		return "MailBox [mailboxId=" + mailboxId + ", mails=" + mails.size() + "]";
	}
	
}
