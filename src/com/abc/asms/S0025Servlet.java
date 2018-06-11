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

import project2.utils.DBUtils;

@WebServlet("/S0025.html")
public class S0025Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getServletContext().getRequestDispatcher("/WEB-INF/s0025.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//  削除実行

//		if(!Utils.checkLogin(req, resp)) {
//		return;
//	} ログインチェック(コピペ)

		req.setCharacterEncoding("utf-8");

//		HttpSession session = req.getSession();
//
		String id = req.getParameter("id");
//
//		List<String> errors = validate(id);
//		if(errors.size() > 0) {
//			session.setAttribute("errors", errors);
//			resp.sendRedirect("index.html");
//			return;
//		} エラーチェック（コピペ）

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

//			List<String> successes = new ArrayList<>();
//			successes.add("削除しました。");
//			session.setAttribute("successes", successes);

			resp.sendRedirect("S0022.html");

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

}
