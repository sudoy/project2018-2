package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project2.beans.Accounts;
import project2.utils.DBUtils;

@WebServlet("/S0043.html")
public class S0043Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();

			sql = "select * from accounts where account_id = ?";
			//select命令の準備
			ps = con.prepareStatement(sql);

			//select文にパラメータの内容をセット
			ps.setString(1, req.getParameter("account_id"));

			//select命令を実行
			rs = ps.executeQuery();

			rs.next();

			int accountId = rs.getInt("account_id");
			String name = rs.getString("name");
			String mail = rs.getString("mail");
			String password = rs.getString("password");
			int authority = rs.getInt("authority");

			Accounts accounts = new Accounts(accountId, name, mail, password, authority);
			req.setAttribute("accounts", accounts);

			//JSPへフォワード
			getServletContext().getRequestDispatcher("/WEB-INF/s0043.jsp")
					.forward(req, resp);

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				if (rs != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
				DBUtils.close(con);
			} catch (Exception e) {
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		//		HttpSession session = req.getSession();

		String accountId = req.getParameter("account_id");
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String authority = req.getParameter("authority");

		//バリデーションチェック
		//		List<String> errors = validate(id, dating, inOut, category, memo, money);
		//		if (errors.size() > 0) {
		//			session.setAttribute("errors", errors);
		//			getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
		//			return;
		//		}

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = project2.utils.DBUtils.getConnection();

			sql = "update accounts set name = ?, mail = ?, password = ?, authority = ? where account_id = ?;";
			ps = con.prepareStatement(sql);

			//insert命令にポストデータの内容をセット
			ps.setString(1, name);
			ps.setString(2, mail);
			ps.setString(3, password);
			ps.setString(4, authority);
			ps.setString(5, accountId);

			ps.executeUpdate();

			//成功メッセージ
			//			List<String> successes = new ArrayList<>();
			//			
			//			if(category.equals("1")) {
			//				category="食費";
			//			}else if (category.equals("2")) {
			//				category = "日用品";
			//			}else if(category.equals("3")) {
			//				category = "交際費";
			//			}else {
			//				return;
			//			}
			//			successes.add("更新しました。");
			//			session.setAttribute("successes", successes);

			resp.sendRedirect("S0042.html");
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
			}
		}
	}
}
