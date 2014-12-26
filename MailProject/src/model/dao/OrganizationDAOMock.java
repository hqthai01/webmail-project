package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.Organization;

public class OrganizationDAOMock {
	public static List<Organization> getOrganizations(){
		try {
			List<Organization> list = new ArrayList<Organization>();
			
//			list.add(new Organization("abc.org", "ABC ORGANIZATION", "abc.crt"));
//			list.add(new Organization("xyz.com.au", "XYZ ORGANIZATION", "xyz.crt"));
//			list.add(new Organization("domain.net", "DOMAIN ORGANIZATION", "domain.crt"));
//			list.add(new Organization("blabla.blabla", "BLABALA ORGANIZATION", "blabla.crt"));
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
