package com.abc.asms.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public static String formatCommaC(Detail_beans dbean) {
		return String.format("%,d", dbean.getUnit_price());
	}
	public static String formatCommaN(Detail_beans dbean) {
		return String.format("%,d", dbean.getSale_number());
	}
	public static String formatCommaSum(Detail_beans dbean) {
		return String.format("%,d", dbean.getSum());
	}

	public static String makeOptionCategories(String value) {
		StringBuilder sb = new StringBuilder();
		sb.append("<option value='0'>選択してください</option>");

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtils.getConnection();

			String sql = "SELECT id, category_name FROM categories ORDER BY id";

			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				String selected = "";
				if(value.equals(rs.getString("id"))) {
					selected = "selected";
				}
				sb.append(String.format(
						"<option value='%d' %s>%s</option>", rs.getInt("id"), selected, rs.getString("type")
					));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtils.close(con);
				DBUtils.close(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


		return sb.toString();
	}

	public static String selectCategory(String param, String value) {
		if(param.equals(value)) {
			return "selected";
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
}
