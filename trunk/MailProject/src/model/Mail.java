package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Mail implements Serializable {
	private static final long serialVersionUID = 0xAF1L;

	public static final int FLAG_UNREAD = 0x3A6;
	public static final int FLAG_READ = 0x3A7;
	public static final int FLAG_SENT = 0x3A8;

	private int mailId;
	private String from;
	private String to;
	private String subject;
	private String message;
	private Date date;
	private MailBox mailbox;
	private int flag;

	private List<String> listAttachment;
	
	

	@Override
	public String toString() {
		return "Mail " + "[from=" + from + ", " + "to=" + to + ", " + "subject=" + subject + ", message=" + message + ", attachments=" + listAttachment.size() + ", flag=" + flag + ", date=" + date
				+ "]";
	}



	@Id
	@GeneratedValue
	public int getMailId() {
		return mailId;
	}



	public void setMailId(int mailId) {
		this.mailId = mailId;
	}



	public String getFrom() {
		return from;
	}



	public void setFrom(String from) {
		this.from = from;
	}



	public String getTo() {
		return to;
	}



	public void setTo(String to) {
		this.to = to;
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
	@JoinColumn(name="mailbox_id")
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



	
	public List<String> getListAttachment() {
		return listAttachment;
	}



	public void setListAttachment(List<String> listAttachment) {
		this.listAttachment = listAttachment;
	}
}
