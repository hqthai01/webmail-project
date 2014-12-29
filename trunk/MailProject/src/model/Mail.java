package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class Mail implements Serializable {
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

	private List<Attachment> attachments;
	
	public Mail() {
		attachments = new ArrayList<Attachment>();
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

	@OneToMany(targetEntity = Attachment.class, mappedBy = "mail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

}
