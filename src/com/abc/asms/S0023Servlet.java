package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.asms.beans.Detail_beans;
import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}

		//権限チェック
		if(!AuthorityUtils.checkSalesAuthority(req, resp)) {
			return;
		}

		req.setCharacterEncoding("utf-8");

		String id = req.getParameter("id");

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql;

		DBUtils.getCategoriesAndAccounts(req, resp);

		try {
			con = DBUtils.getConnection();

			sql = "select sale_id, sale_date, name,"
					+" category_id, a.account_id,"
					+" trade_name, unit_price, sale_number,"
					+" note from sales s JOIN accounts a ON s.account_id = a.account_id"
					+" where sale_id = ?";

			ps = con.prepareStatement(sql);

			ps.setString(1, id);

			rs = ps.executeQuery();

			if(!rs.next()) {
				throw new Exception();
			}

			Detail_beans s23 = new Detail_beans(
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

			req.setAttribute("s23", s23);

			req.getServletContext().getRequestDispatcher("/WEB-INF/s0023.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}finally {
			try {
				DBUtils.close(con);
				DBUtils.close(ps);
				DBUtils.close(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}


