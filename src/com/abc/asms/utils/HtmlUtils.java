package com.abc.asms.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.abc.asms.beans.S0021;

public class HtmlUtils {
// / 区切りのメソッド
	public static String formatDate(LocalDate saleDate) {

		LocalDate line = saleDate;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return line.format(dtf);
	}

//カンマ区切りのメソッド
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
}
