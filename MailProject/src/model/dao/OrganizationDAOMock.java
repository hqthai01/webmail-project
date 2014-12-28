package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.Organization;

public class OrganizationDAOMock {
	private static List<Organization> list;

	public static List<Organization> getOrganizations() {
		try {
			list = new ArrayList<Organization>();
			Organization o = new Organization();
			o.setOrgDomain("abc.org");
			list.add(o);

			o = new Organization();
			o.setOrgDomain("xyz.org");
			list.add(o);

			o = new Organization();
			o.setOrgDomain("domain.org");
			list.add(o);

			o = new Organization();
			o.setOrgDomain("blabla.org");
			list.add(o);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Organization getOrganization(String orgDomain) {
		for (Organization org : list) {
			if (org.getOrgDomain().equals(orgDomain)) {
				return org;
			}
		}
		return null;
	}
	
	public static boolean isExistOrg(String domainName){
		return false;
	}
}
