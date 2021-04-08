package com.xifeng.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {

	public static boolean isEmail(String email) {
		if(null == email || "".equals(email.trim())) {
			return false;
		}
		String regexp = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static boolean isMobile(String value) {
		if (value != null && value.length() == 11 && value.startsWith("1")) {
			return isNumeric(value);
		}
		return false;
	}

	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isHomeTel(String value) {
		if (value != null && value.length() > 7) {
			if(!value.startsWith("1")) {
				return isNumeric(value);
			}
			
		}
		return false;
	}
}
