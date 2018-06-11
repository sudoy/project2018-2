package project2.utils;

import java.sql.Date;
import java.time.LocalDate;

public class Utils {
	public static LocalDate date2LocalDate(final Date date) {
		if(date == null) {
			return null;
		}
		return date.toLocalDate();
	}
}
