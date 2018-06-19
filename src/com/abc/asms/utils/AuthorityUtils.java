package com.abc.asms.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.beans.Accounts;

public class AuthorityUtils {
	public static boolean checkAccountEditAuthority(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		HttpSession session = req.getSession();
		Accounts accounts = (Accounts)session.getAttribute("accounts");
		int authorityaccountEditAuthority = accounts.getAuthority();
		String authority = String.valueOf(authorityaccountEditAuthority);
		if (!authority.equals("10") && !authority.equals("11")) {
			List<String> errors = new ArrayList<>();
			errors.add("不正なアクセスです。");
			session.setAttribute("errors", errors);
			resp.sendRedirect("C0020.html");
			return false;
		}else {
			return true;
		}

	}

	//売上登録の権限チェック
	public static boolean checkSalesAuthority(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		HttpSession session = req.getSession();
		Accounts accounts = (Accounts)session.getAttribute("accounts");
		int authority = accounts.getAuthority();
		String salesAuthority = String.valueOf(authority);
		if (!salesAuthority.equals("1") && !salesAuthority.equals("11")) {
			List<String> errors = new ArrayList<>();
			errors.add("不正なアクセスです。");
			session.setAttribute("errors", errors);
			resp.sendRedirect("C0020.html");
			return false;
		}else {
			return true;
		}

	}

	public static boolean tabDeleteAccountAuthority(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		HttpSession session = req.getSession();
		Accounts accounts = (Accounts)session.getAttribute("accounts");
		int authorityaccountEditAuthority = accounts.getAuthority();
		String authority = String.valueOf(authorityaccountEditAuthority);
		if (!authority.equals("10") && !authority.equals("11")) {

			return false;
		}else {
			return true;
		}

	}



	public static String conversionAuthority(String authority) {
		if (authority.equals("0")) {
			return "権限なし";
		} else if (authority.equals("1")) {
			return "売上登録";
		} else if (authority.equals("10")) {
			return "アカウント登録";
		} else {
			return "売上登録/アカウント登録";
		}
	}

	public static String checkAuthority1(String authority) {
		if (authority.equals("1") || authority.equals("11") || authority.equals("")) {
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
		if (authority.equals("10") || authority.equals("11")) {
			return "";
		} else {
			return "checked";
		}

	}

	public static String checkAuthority4(String authority) {
		if (authority.equals("10") || authority.equals("11")) {
			return "checked";
		} else {
			return "";
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

	public static String checkAuthority3a(String authority) {
		if (authority.equals("0")) {
			return "1";
		} else {
			return "";
		}
	}

	public static String checkAuthority4a(String authority) {
		if (authority.equals("1")) {
			return "10";
		} else {
			return "";
		}
	}

}
