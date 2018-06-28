package com.abc.asms.utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class HtmlUtils {
	//区切りのメソッド
	public static String formatDate(LocalDate saleDate) {

		LocalDate line = saleDate;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/M/d");
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

	public static boolean checkCategory(String[] param, String value) {
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
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
		 return sdf.format(d);

	}

	public static String formName(String name) {
		if(name.contains("<") || name.contains(">") || name.contains("&")) {
			name = name.replaceAll("<", "&lt;");
			name = name.replaceAll(">", "&gt;");
			name = name.replaceAll("&", "&amp;");
		}
		return name;
	}


	public static double taxPrice(int price, int number, Date d) throws ParseException {
		DateFormat dateTimeFormat = new SimpleDateFormat("yyyy/M/d");
		java.util.Date date1 = dateTimeFormat.parse("2019/10/1");
		java.util.Date date2 = dateTimeFormat.parse("2019/9/30");
		double sum;
		double tax;

		// ~2019/9/30だったら税率8%
		if(d.before(date1)) {
			tax = 1.08;
		// 2019/10/1~だったら税率10%
		}else if(d.after(date2)){
			tax = 1.10;
		}else {
			tax = 0;
		}

		sum = price * number * tax;
		return sum;

	}

	public static double taxPrice1(String price, String number, String d) throws ParseException {
		int price1 = Integer.parseInt(price);
		int number1 = Integer.parseInt(number);
		DateFormat dateTimeFormat = new SimpleDateFormat("yyyy/M/d");
		java.util.Date date1 = dateTimeFormat.parse("2019/10/1");
		java.util.Date date2 = dateTimeFormat.parse("2019/9/30");
		java.util.Date d1 = dateTimeFormat.parse(d);
		double sum;
		double tax;

		// ~2019/9/30だったら税率8%
		if(d1.before(date1)) {
			tax = 1.08;
		// 2019/10/1~だったら税率10%
		}else if(d1.after(date2)){
			tax = 1.10;
		}else {
			tax = 0;
		}

		sum = price1 * number1 * tax;
		return sum;

	}

}
