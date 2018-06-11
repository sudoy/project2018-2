package project2.utils;

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
	public static String checkAutority1(String authority) {
		if (authority.equals("1") || authority.equals("11")) {
			return "";
		} else {
			return "cheked";
		}
}
	public static String checkAutority2(String authority) {
		if (authority.equals("1") || authority.equals("11")) {
			return "checed";
		} else {
			return "";
		}
}
	public static String checkAutority3(String authority) {
		if (authority.equals("0") || authority.equals("1")) {
			return "checked";
		} else {
			return "";
		}
	
}
	public static String checkAutority4(String authority) {
		if (authority.equals("01") || authority.equals("11")) {
			return "checked";
		} else {
			return "";
		}
	
}
}
