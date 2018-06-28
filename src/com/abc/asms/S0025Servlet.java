package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.beans.DetailBeans;
import com.abc.asms.beans.SearchKeepSale;
import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0025.html")
public class S0025Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}

		//権限チェック
		if (!AuthorityUtils.checkSalesAuthority(req, resp)) {
			return;
		}

		String id = req.getParameter("id");

		HttpSession session = req.getSession();
		SearchKeepSale ss = (SearchKeepSale)session.getAttribute("ss");
		req.setAttribute("ss", ss);

		if(ss == null) {
			resp.sendRedirect("S0020.html");
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;

		//カテゴリーテーブルとアカウントテーブルの呼び出し
		DBUtils.getCategoriesAndAccountsForAll(req, resp);

		//DB接続とSQL
		try {
			con = DBUtils.getConnection();

			sql = "select sale_id, sale_date, name,"
					+ " category_id, a.account_id,"
					+ " trade_name, unit_price,sale_number,"
					+ " note from sales s JOIN accounts a ON s.account_id = a.account_id"
					+ " where sale_id = ?";

			ps = con.prepareStatement(sql);

			ps.setString(1, id);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new Exception();
			}

			DetailBeans s25 = new DetailBeans(
					rs.getInt("sale_id"),
					rs.getDate("sale_date"),
					rs.getString("name"),
					rs.getInt("category_id"),
					rs.getInt("account_id"),
					rs.getString("trade_name"),
					rs.getInt("unit_price"),
					rs.getInt("sale_number"),
					rs.getString("note")
					);

			req.setAttribute("s25", s25);

			req.getServletContext().getRequestDispatcher("/WEB-INF/s0025.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		} finally {
			try {
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}

		//権限チェック
		if (!AuthorityUtils.checkSalesAuthority(req, resp)) {
			return;
		}

		req.setCharacterEncoding("utf-8");

		HttpSession session = req.getSession();

		String id = req.getParameter("id");

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtils.getConnection();

			String sql = ""
					+ "delete from sales"
					+ " where sale_id = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			ps.executeUpdate();

			List<String> successes = new ArrayList<>();
			successes.add("No." + id + "の売上を削除しました。");
			session.setAttribute("successes", successes);

			resp.sendRedirect("S0021.html");

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		} finally {
			try {
				DBUtils.close(ps);
				DBUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
