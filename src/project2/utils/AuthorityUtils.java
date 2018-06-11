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
}
