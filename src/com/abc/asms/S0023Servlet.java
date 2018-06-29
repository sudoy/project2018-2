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

import com.abc.asms.beans.CancelBeans;
import com.abc.asms.beans.DetailBeans;
import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		HttpSession session = req.getSession();

		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}

		//権限チェック
		if(!AuthorityUtils.checkSalesAuthority(req, resp)) {
			return;
		}

		CancelBeans data = (CancelBeans) session.getAttribute("data");

		session.setAttribute("data", data);

		if(req.getParameter("id") == null) {
			List<String> errors = new ArrayList<>();
			errors.add("不正なアクセスです。");
			session.setAttribute("errors", errors);
			resp.sendRedirect("S0020.html");
			return ;
		}

		//アカウントとカテゴリーの情報取得
		DBUtils.getCategoriesAndAccounts(req, resp);

		getServletContext().getRequestDispatcher("/WEB-INF/s0023.jsp").forward(req, resp);

	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
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
					+" trade_name, unit_price,sale_number, unit_price * sale_number as sum,"
					+" note from sales s LEFT JOIN accounts a ON s.account_id = a.account_id"
					+" where sale_id = ?";

			ps = con.prepareStatement(sql);

			ps.setString(1, id);

			rs = ps.executeQuery();

			if(!rs.next()) {
				throw new Exception();
			}

			DetailBeans s23 = new DetailBeans(
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


