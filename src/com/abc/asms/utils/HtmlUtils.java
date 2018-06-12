package com.abc.asms.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.abc.asms.beans.Detail_beans;

public class HtmlUtils {
	public static String formatDate(Detail_beans dbean) {

		LocalDate line = dbean.getSale_date();


//		if(line == null) {
//			return "";
//		}

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return line.format(dtf);

	}
}
