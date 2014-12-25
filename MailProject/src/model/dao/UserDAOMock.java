package model.dao;

import model.Organization;
import model.User;

public class UserDAOMock {
	public static User getUser(String username){
		User usr = new User();
		Organization org = new Organization("cateam.com", "CA Team", null);
		usr.setAccount("hqthai01");
		usr.setPassword("holdholdme");
		usr.setFullname("HQ.THAI");
//		usr.setMailBox(MailBoxDAOMock.getMailBox(usr.getAccount()));
		usr.setOrg(org);
		usr.setPhone("01636755316");
		return usr;
	}
}
