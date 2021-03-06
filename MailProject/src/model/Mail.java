package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Mail implements Serializable, Comparable<Mail>{

//	@Override
//	public int compare(Mail o1, Mail o2) {
//		return o1.getDate().compareTo(o2.getDate());
//	}

	@Override
	public int compareTo(Mail o) {
		return o.getDate().compareTo(this.getDate());
	}

	private static final long serialVersionUID = 0xAF1L;

	public static final int FLAG_UNREAD = 0x3A6;
	public static final int FLAG_READ = 0x3A7;
	public static final int FLAG_SENT = 0x3A8;

	private int id;
	private String mail_from;
	private String mail_to;
	private String subject;
	private String message;
	private Date date;

	private MailBox mailbox;
	private int flag;

	private Set<Attachment> attachments;
	
	public Mail() {
		attachments = new HashSet<Attachment>();
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int Id) {
		this.id = Id;
	}

	public String getMail_From() {
		return mail_from;
	}

	@Column(name="mail_from")
	public void setMail_From(String from) {
		this.mail_from = from;
	}

	@Column(name="mail_to")
	public String getMail_To() {
		return mail_to;
	}

	public void setMail_To(String to) {
		this.mail_to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(columnDefinition="TEXT")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne
	@JoinColumn(name = "mailbox_id")
	public MailBox getMailbox() {
		return mailbox;
	}

	public void setMailbox(MailBox mailbox) {
		this.mailbox = mailbox;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@OneToMany(targetEntity = Attachment.class, mappedBy = "mail", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "Mail [id=" + id + ", mail_from=" + mail_from + ", mail_to=" + mail_to + ", subject=" + subject + ", message=" + message + ", date=" + date + ", mailbox=" + mailbox + ", flag="
				+ flag + ", attachments=" + attachments.size() + "]";
	}

}
