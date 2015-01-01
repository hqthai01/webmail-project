package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	public static boolean isNumber(String number) {
		try {
			String regex = "\\d+";
			Pattern p = Pattern.compile(regex);
			Matcher matcher = p.matcher(number);
			return matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isMailAddress(String mailAddress){
		try {
			String regex="\\w+@\\w+(\\.\\w+)+";
			Pattern p = Pattern.compile(regex);
			Matcher matcher = p.matcher(mailAddress);
			return matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
