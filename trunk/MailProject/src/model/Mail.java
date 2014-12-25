package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Mail implements Serializable {
	private static final long serialVersionUID = 0xAF1L;

	public static final int FLAG_UNREAD = 0x3A6;
	public static final int FLAG_READ = 0x3A7;
	public static final int FLAG_SENT = 0x3A8;

	private int id;
	private String from;
	private String to;
	private String subject;
	private String message;
	private Date date;

	private int flag;

	private List<String> listAttachment;

	public Mail(String from, String to, String subject, String message, List<String> listAttachment, int flag) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.message = message;
		this.listAttachment = listAttachment;
		this.flag = flag;
		this.date = new Date();
	}

	public Mail() {
		this.listAttachment = new ArrayList<String>();
		this.date = new Date();
	}

	public int getFlag() {
		return flag;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void addAttchment(String filename) {
		listAttachment.add(filename);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public List<String> getListAttachment() {
		return listAttachment;
	}

	public void setListAttachment(List<String> listAttachment) {
		this.listAttachment = listAttachment;
	}

	@Override
	public String toString() {
		return "Mail " + "[from=" + from + ", " + "to=" + to + ", " + "subject=" + subject + ", message=" + message + ", attachments=" + listAttachment.size() + ", flag=" + flag + ", date=" + date
				+ "]";
	}
}
