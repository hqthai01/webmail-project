package model.dao;

import model.Organization;
import model.User;

public class UserDAOMock {
	public static User getUser(String username){
		User usr = new User();
		Organization org = new Organization();
		usr.setAccount("hqthai01");
		usr.setPassword("holdholdme");
		usr.setFullname("HQ.THAI");
		usr.setMailBox(MailBoxDAOMock.getMailBox(usr.getAccount()));
		usr.setOrg(org);
		usr.setPhone("01636755316");
		return usr;
	}
	
	public static boolean checkUser(String username, String password){
		return username.equalsIgnoreCase("hqthai01") && password.equals("holdholdme");
	}

	public static boolean isExist(String username){
		if(username.equals("hqthai01")){
			return true;
		}
		return false;
	}
	
	public static int createNewUser(User user){
		return 1;
	}
}
