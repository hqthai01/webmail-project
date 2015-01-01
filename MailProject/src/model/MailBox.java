package model;

import java.io.Serializable;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

@Entity
public class MailBox implements Serializable {

	private static final long serialVersionUID = 0xAF1L;

	private int mailboxId;
	private SortedSet<Mail> mails;
	
	public MailBox(){
		mails = new TreeSet<Mail>();
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
	@Sort(type=SortType.NATURAL)
	public Set<Mail> getMails() {
		return mails;
	}

	public void setMails(SortedSet<Mail> mails) {
		this.mails = mails;
	}

	@Override
	public String toString() {
		return "MailBox [mailboxId=" + mailboxId + ", mails=" + mails.size() + "]";
	}
	
}
