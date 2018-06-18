package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.beans.Accounts;
import com.abc.asms.beans.C0020;
import com.abc.asms.utils.DBUtils;

@WebServlet("/C0020.html")
public class C0020Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// localDateから現在時刻を抽出するか否かの判定
		LocalDate ld = null;

		// どの変数を見るか選択
		String check = null;

		if(req.getParameter("back") != null) {
			check = req.getParameter("back");
		} else if(req.getParameter("next") != null) {
			check = req.getParameter("next");
		} else if(req.getParameter("before") != null) {
			check = req.getParameter("before");
		} else if(req.getParameter("after") != null) {
			check = req.getParameter("after");
		}

		// checkに何もない場合現在日時抽出、ある場合はLocalDateに変換
		if(check != null) {
			ld = LocalDate.parse(check + "01日", DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
		} else {
			ld = LocalDate.now();
		}

		// 表示月とその月初末の変数宣言
		String today = null;
		LocalDate first = null;
		LocalDate last = null;

		// 前月と翌月のパラメータ取得

		if(req.getParameter("back") != null) {
			// 前月
			today = DateTimeFormatter.ofPattern("yyyy年MM月").format(ld.minusMonths(1));
			first = ld.withDayOfMonth(1).minusMonths(1);
			last = ld.withDayOfMonth(1).minusDays(1);
		} else if(req.getParameter("next") != null) {
			// 翌月
			today = DateTimeFormatter.ofPattern("yyyy年MM月").format(ld.plusMonths(1));
			first = ld.withDayOfMonth(1).plusMonths(1);
			last = ld.withDayOfMonth(1).plusMonths(2).minusDays(1);
		} else if(req.getParameter("before") != null) {
			// 前年
			today = DateTimeFormatter.ofPattern("yyyy年MM月").format(ld.minusYears(1));
			first = ld.withDayOfMonth(1).minusYears(1);
			last = ld.withDayOfMonth(1).plusMonths(1).minusDays(1).minusYears(1);
		} else if(req.getParameter("after") != null) {
			// 翌年
			today = DateTimeFormatter.ofPattern("yyyy年MM月").format(ld.plusYears(1));
			first = ld.withDayOfMonth(1).plusYears(1);
			last = ld.withDayOfMonth(1).plusMonths(1).minusDays(1).plusYears(1);
		} else {
			// 今月
			today = DateTimeFormatter.ofPattern("yyyy年MM月").format(ld);
			first = ld.withDayOfMonth(1);
			last = ld.withDayOfMonth(1).plusMonths(1).minusDays(1);
		};

		LocalDate lastMonthFirst = ld.withDayOfMonth(1).minusMonths(1);
		LocalDate lastMonthLast = ld.withDayOfMonth(1).minusDays(1);

		int thisMonthSum = 0;
		int lastMonthSum = 0;

		//セッションに保存されたアカウント情報を持ってくる
		HttpSession session = req.getSession();
		Accounts accounts = (Accounts)session.getAttribute("accounts");
		int accountId = accounts.getAccountId();
		String strAccountId = String.valueOf(accountId);

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try{
			con = DBUtils.getConnection();

			sql = "SELECT SUM(unit_price * sale_number) AS sum1 FROM sales WHERE sale_date between ? and ?;";

			ps = con.prepareStatement(sql);

			ps.setString(1, first.toString());
			ps.setString(2, last.toString());

			rs = ps.executeQuery();

			if(rs.next()) {
				thisMonthSum = rs.getInt("sum1");

				req.setAttribute("thisMonthSum", thisMonthSum);
			}

			try{
				DBUtils.close(ps);
				DBUtils.close(rs);
			}catch(Exception e){}

			sql = "SELECT SUM(unit_price * sale_number) AS sum2 FROM sales WHERE sale_date between ? and ?;";

			ps = con.prepareStatement(sql);

			ps.setString(1, lastMonthFirst.toString());
			ps.setString(2, lastMonthLast.toString());

			rs = ps.executeQuery();

			if(rs.next()) {
				lastMonthSum = rs.getInt("sum2");

				req.setAttribute("lastMonthSum", lastMonthSum);
			}

			try{
				DBUtils.close(ps);
				DBUtils.close(rs);
			}catch(Exception e){}

			sql = "select * from accounts a "
					+"left join sales s on s.account_id = a.account_id "
					+"left join categories c on c.category_id = s.category_id "
					+ "where s.sale_date between ? and ? and a.account_id = ?"
					+ "order by s.sale_date;";

			ps = con.prepareStatement(sql);

			ps.setString(1, first.toString());
			ps.setString(2, last.toString());
			ps.setString(3, strAccountId);

			rs = ps.executeQuery();

			//ResultSetをJavaBeansに変換
			List<C0020> list = new ArrayList<>();

			while(rs.next()) {
				C0020 c0020 = new C0020(rs.getInt("sale_id"),
							rs.getDate("sale_date"),
							rs.getString("category_name"),
							rs.getString("trade_name"),
							rs.getInt("unit_price"),
							rs.getInt("sale_number")
						);

				list.add(c0020);

			}

			//JavaBeansをJSPへ渡す
			req.setAttribute("list", list);
			req.setAttribute("today", today);

		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){
			}
		}

		//foward→c0020.jsp
		getServletContext().getRequestDispatcher("/WEB-INF/c0020.jsp")
			.forward(req, resp);

	}
}
