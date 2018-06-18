package com.abc.asms.utils;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Utils {
	public static LocalDate date2LocalDate(final Date date) {
		if(date == null) {
			return null;
		}
		return date.toLocalDate();
	}

	public static boolean checkLogin(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession();

		//ログインチェック
		if(session.getAttribute("accounts") == null) {
			//ログインしていない
			List<String> errors = new ArrayList<>();
			errors.add("ログインして下さい。");
			session.setAttribute("errors", errors);
			resp.sendRedirect("C0010.html");
			return false;

		}else {
			return true;
		}

	}
}
