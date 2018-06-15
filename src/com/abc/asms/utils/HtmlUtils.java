package com.abc.asms.utils;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.beans.Detail_beans;
import com.abc.asms.beans.S0021;
import com.abc.asms.beans.S0022;

public class HtmlUtils {
	public static String formatDate(Detail_beans dbean) {

		LocalDate line = dbean.getSale_date();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return line.format(dtf);

	}

	public static String formatDate(S0022 s22) {

		LocalDate line = s22.getSale_date();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return line.format(dtf);

	}


	public static String formatCommaC(Detail_beans dbean) {
		return String.format("%,d", dbean.getUnit_price());
	}
	public static String formatCommaN(Detail_beans dbean) {
		return String.format("%,d", dbean.getSale_number());
	}
	public static String formatCommaSum(Detail_beans dbean) {
		return String.format("%,d", dbean.getSum());
	}

	public static String formatCommaC(S0022 s22) {
		return String.format("%,d", s22.getUnit_price());
	}
	public static String formatCommaN(S0022 s22) {
		return String.format("%,d", s22.getSale_number());
	}
	public static String formatCommaSum(S0022 s22) {
		return String.format("%,d", s22.getSum());
	}


	public static String formatComma(int value) {
		return String.format("%,d", value);
	}

	public static String selectCategory(String param, String value) {
		if(param.equals(value)) {
			return "selected";
		}else {
			return "";
		}
	}

	public static String checkCategory(String param, String value) {
		if(param.equals(value)) {
			return "checked";
		}else {
			return "";
		}
	}

	public static String selectName(String param, String value) {
		if(param.equals(value)) {
			return "selected";
		}else {
			return "";
		}
	}
	public static String formDate(Date d) {
		if(d == null) {
			return "";
		}
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		 return sdf.format(d);

	}

	public static String formatUnitPrice(int price) {
		S0021 s = new S0021(price, null, null, null, null, price,price, null);
		String str = String.format("%,3d", s.getUnitPrice());
		return str;
	}
	public static String formatSaleNumber(int price) {
		S0021 s = new S0021(price, null, null, null, null, price,price, null);
		String str = String.format("%,3d", s.getSaleNumber());
		return str;
	}
	public static String formatTotal(int price) {
		S0021 s = new S0021(price, null, null, null, null, price,price, null);
		String str = String.format("%,3d", s.getSaleNumber());
		return str;
	}
	public static boolean checkCategory1(String[] param, String value) {
		if(param == null) {
			return true;
		}
		for(String s : param) {
			if(s.equals(value)) {
				return true;
			}
		}
		return false;

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
