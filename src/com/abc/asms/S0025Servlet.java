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
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0025.html")
public class S0025Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//  削除実行のプログラム

//			if(!Utils.checkLogin(req, resp)) {
//			return;
//		} ログインチェック(コピペ)

			req.setCharacterEncoding("utf-8");

//			HttpSession session = req.getSession();
	//
			String id = req.getParameter("id");
	//
//			List<String> errors = validate(id);
//			if(errors.size() > 0) {
//				session.setAttribute("errors", errors);
//				resp.sendRedirect("index.html");
//				return;
//			} エラーチェック（コピペ）

			Connection con = null;
			PreparedStatement ps = null;

			try {
				con = DBUtils.getConnection();

				String sql = ""
							+"delete from sales"
							+" where sale_id = ?";

				ps = con.prepareStatement(sql);
				ps.setString(1, id);

				ps.executeUpdate();

//				List<String> successes = new ArrayList<>();
//				successes.add("削除しました。");
//				session.setAttribute("successes", successes);

				resp.sendRedirect("S0021.html");

			}catch(Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}finally {
				try {
					DBUtils.close(con);
					DBUtils.close(ps);
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String id = req.getParameter("id");

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();

			String sql = "select sale_id, sale_date, name,"
						+" (select category_name from categories c where s.category_id = c.category_id) as category_name,"
						+" trade_name, unit_price,sale_number, unit_price * sale_number as sum,"
						+" note from sales s JOIN accounts a ON s.account_id = a.account_id"
						+" where sale_id = ?";

			ps = con.prepareStatement(sql);

			ps.setString(1, id);
			System.out.println(ps);

			rs = ps.executeQuery();

			if(!rs.next()) {
				throw new Exception();
			}

//			System.out.println(rs.getDate("sale_date"));

			Detail_beans s25 = new Detail_beans(
					Utils.date2LocalDate(rs.getDate("sale_date")),
					rs.getString("name"),
					rs.getString("category_name"),
					rs.getString("trade_name"),
					rs.getInt("unit_price"),
					rs.getInt("sale_number"),
					rs.getInt("sum"),
					rs.getString("note"),
					rs.getInt("sale_id")
				);


			req.setAttribute("s25", s25);

			req.getServletContext().getRequestDispatcher("/WEB-INF/s0025.jsp").forward(req, resp);

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
//		resp.sendRedirect("S0022.html");
		req.getServletContext().getRequestDispatcher("/WEB-INF/s0025.jsp").forward(req, resp);
	}


}