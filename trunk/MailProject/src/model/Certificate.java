package model;

import java.io.Serializable;

public class Certificate implements Serializable {
	private static final long serialVersionUID = 0xABE1L;

	private int id;
	private String fileName;
	private String filePath;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "Certificate [id=" + id + ", fileName=" + fileName + ", filePath=" + filePath + "]";
	}

}
