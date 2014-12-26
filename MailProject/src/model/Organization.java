package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Organization implements Serializable {
	private static final long serialVersionUID = 0xA1L;

	private String orgDomain;
	private String orgName;
	private Certificate certificate;

	@Id
	@GeneratedValue
	public String getOrgDomain() {
		return orgDomain;
	}

	public void setOrgDomain(String orgDomain) {
		this.orgDomain = orgDomain;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	@Override
	public String toString() {
		return "Organization [orgDomain=" + orgDomain + ", orgName=" + orgName + ", certificate=" + certificate.getFileName() + "]";
	}

}
