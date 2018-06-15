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

import com.abc.asms.beans.Accounts;
import com.abc.asms.beans.Categories;
import com.abc.asms.beans.Detail_beans;
import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.HtmlUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//ログインチェック
		if (!HtmlUtils.checkLogin(req, resp)) {
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

		//categoriesTableからデータ取得
		try{
			con = DBUtils.getConnection();

			//SQL
			sql = "select category_id,category_name, active_flg from categories where active_flg = 1";
			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//SELECT命令を実行
			rs = ps.executeQuery();

			List<Categories> categories = new ArrayList<>();
			while(rs.next()) {
				Categories category = new Categories(
						rs.getInt("category_id"),
						rs.getString("category_name"),
						rs.getInt("active_flg"));
				categories.add(category);
			}

			req.setAttribute("categories", categories);

		} catch (Exception e) {
			throw new ServletException(e);

		}finally {
				try {
					DBUtils.close(con);
					DBUtils.close(ps);
					DBUtils.close(rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		try{

		con = DBUtils.getConnection();

		sql = "select account_id,name,mail,password,authority from accounts";
		//SELECT命令の準備
		ps = con.prepareStatement(sql);
		//SELECT命令を実行
		rs = ps.executeQuery();
		List<Accounts> accounts = new ArrayList<>();
		while(rs.next()) {
			Accounts account = new Accounts(
					rs.getInt("account_id"),
					rs.getString("name"),
					rs.getString("mail"),
					rs.getString("password"),
					rs.getInt("authority"));

			accounts.add(account);
		}
			req.setAttribute("accounts", accounts);

		} catch (Exception e) {
			throw new ServletException(e);

		}finally {
				try {
					DBUtils.close(con);
					DBUtils.close(ps);
					DBUtils.close(rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		String sale_date = req.getParameter("sale_date");

		try {
			con = DBUtils.getConnection();

			sql = "select sale_id, sale_date, name,"
					+" category_id, a.account_id,"
					+" trade_name, unit_price,sale_number, unit_price * sale_number as sum,"
					+" note from sales s JOIN accounts a ON s.account_id = a.account_id"
					+" where sale_id = ?";

			ps = con.prepareStatement(sql);

			ps.setString(1, id);

			rs = ps.executeQuery();

			if(!rs.next()) {
				throw new Exception();
			}

			Detail_beans s23 = new Detail_beans(
					Utils.date2LocalDate(rs.getDate("sale_date")),
					rs.getString("name"),
					rs.getInt("category_id"),
					rs.getInt("account_id"),
					rs.getString("trade_name"),
					rs.getInt("unit_price"),
					rs.getInt("sale_number"),
					rs.getInt("sum"),
					rs.getString("note"),
					rs.getInt("sale_id")

				);

			req.setAttribute("s23", s23);

			req.getServletContext().getRequestDispatcher("/WEB-INF/s0023.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
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


