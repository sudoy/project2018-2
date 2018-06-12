package com.abc.asms.utils;

public class AuthorityUtils {
	public static String checkAutority(String param, String value) {
		if (param.equals("") && value.equals("1")) {
			return "checked";
		} else if (param.equals(value)) {
			return "checked";
		} else {
			return "";
		}
	}
	public static String conversionAuthority(String authority){
		if(authority.equals("0")) {
			return "";
		}else if(authority.equals("1")) {
			return "売上登録";
		}else if(authority.equals("10")) {
			return "アカウント登録";
		}else {
			return "売上登録/アカウント登録";
		}
	}
	
	
	public  static String checkAuthority1(String authority) {
		if (authority.equals("1") || authority.equals("11")) {
			return "";
		} else {
			return "checked";
		}
}
	public static String checkAuthority2(String authority) {
		if (authority.equals("1") || authority.equals("11")) {
			return "checked";
		} else {
			return "";
		}
}
	public static String checkAuthority3(String authority) {
		if (authority.equals("0") || authority.equals("1")) {
			return "checked";
		} else {
			return "";
		}
	
}
	public static String checkAuthority4(String authority) {
		if (authority.equals("0") || authority.equals("1")) {
			return "";
		} else {
			return "checked";
		}
	
}
	public static String checkAuthoritya(String authority) {
		if (authority.equals("0")) {
			return "checked";
		} else {
			return "";
		}
}
	public static String checkAuthorityb(String authority) {
		if (authority.equals("1")) {
			return "checked";
		} else {
			return "";
		}
}
}
