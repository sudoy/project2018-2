package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.asms.utils.DBUtils;


@WebServlet("/S0024.html")
public class S0024Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		if(!Utils.checkLogin(req, resp)) {
//			return;
//		}

		String id = req.getParameter("id");

		req.setCharacterEncoding("utf-8");
//		HttpSession session = req.getSession();

		String sale_date = req.getParameter("sale_date");
		String account_id = req.getParameter("account_id");
		String category_id = req.getParameter("category_id");
		String trade_name = req.getParameter("trade_name");
		String unit_price = req.getParameter("unit_price");
		String sale_number = req.getParameter("sale_number");
		String sum = req.getParameter("sum");
		String note = req.getParameter("note");


//		List<String> errors = validate(id, title, deadline, level);
//		System.out.println(errors);
//
//		if(errors.size() > 0) {
//
//			session.setAttribute("errors", errors);
//
//			getServletContext().getRequestDispatcher("/WEB-INF/update.jsp")
//				.forward(req, resp);
//
//			return;
//		}

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DBUtils.getConnection();

//			String sql = ""
//						+ "update sales set "
//						+ "sale_date = ?, account_id = ?, category_id = ?, trade_name = ? "
//						+ "unit_price = ?, sale_number = ?, note = ? "
//						+ "where sale_id = ?";
//
//			ps.setString(1, sale_date);
//			ps.setString(2, account_id);
//			ps.setString(3, category_id);
//			ps.setString(4, trade_name);
//			ps.setString(5, unit_price);
//			ps.setString(6, sale_number);
//			ps.setString(7, note);
//			ps.setString(8, id);

			String sql = ""
					+ "update sales set "
					+ "sale_date = '2018/06/02', account_id = 2, category_id = 1, trade_name = '天丼', "
					+ "unit_price = 450, sale_number = 5, note = '特になし' "
					+ "where sale_id = ?";

			ps = con.prepareStatement(sql);

//			ps.setString(1, sale_date);
//			ps.setString(2, account_id);
//			ps.setString(3, category_id);
//			ps.setString(4, trade_name);
//			ps.setString(5, unit_price);
//			ps.setString(6, sale_number);
//			ps.setString(7, note);
//			ps.setString(8, id);

			ps.setString(1, id);

			System.out.println(ps);

			ps.executeUpdate();

			resp.sendRedirect("S0022.html?id=6");

//			List<String> successes = new ArrayList<>();
//			successes.add("更新しました。");
//			session.setAttribute("successes", successes);
//
//			resp.sendRedirect("index.html");

		} catch (Exception e) {
			throw new ServletException(e);

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

//仮実装
//		String sale_date = req.getParameter("sale_date");
//		String name = req.getParameter("name");
//		String category_name = req.getParameter("category_name");
//		String trade_name = req.getParameter("trade_name");
//		String unit_price = req.getParameter("unit_price");
//		String sale_number = req.getParameter("sale_number");
//		String sum = req.getParameter("sum");
//		String note = req.getParameter("note");
//		String sale_id = req.getParameter("sale_id");
//
//		req.setAttribute("sale_date", sale_date);
//		req.setAttribute("name", name);
//		req.setAttribute("category_name", category_name);
//		req.setAttribute("trade_name ", trade_name );
//		req.setAttribute("unit_price", unit_price);
//		req.setAttribute("sale_number", sale_number);
//		req.setAttribute("sum", sum);
//		req.setAttribute("note", note);
//		req.setAttribute("sale_id", sale_id);





//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		try {
//			con = DBUtils.getConnection();
//
//			String sql = "select sale_id, sale_date, name,"
//						+" (select category_name from categories c where s.category_id = c.category_id) as category_name,"
//						+" trade_name, unit_price,sale_number, unit_price * sale_number as sum,"
//						+" note from sales s JOIN accounts a ON s.account_id = a.account_id"
//						+" where sale_id = ?";
//
//			ps = con.prepareStatement(sql);
//
//			ps.setString(1, id);
//
//			System.out.println(ps);
//
//			rs = ps.executeQuery();
//
//			if(!rs.next()) {
//				throw new Exception();
//			}

//			List<String> s24 = new ArrayList<>();
////					s24.add(sale_date);
//					s24.add(name);
//					s24.add(category_name);
//					s24.add(trade_name);
//					s24.add(unit_price);
//					s24.add(sale_number);
//					s24.add(sum);
//					s24.add(note);
//					s24.add(sale_id);

//			req.setAttribute("s24", s24);
		req.getServletContext().getRequestDispatcher("/WEB-INF/s0024.jsp").forward(req, resp);

	}

}
