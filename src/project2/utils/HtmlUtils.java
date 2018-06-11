package project2.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import project2.beans.Detail_beans;

public class HtmlUtils {
	public static String formatDate(Detail_beans dbean) {
		System.out.println(dbean);
		LocalDate line = dbean.getSale_date();
		System.out.println(dbean);

//		if(line == null) {
//			return "";
//		}

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return line.format(dtf);

	}
}
