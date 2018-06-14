package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.beans.Accounts;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.HtmlUtils;

@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//ログインチェック
		if (!HtmlUtils.checkLogin(req, resp)) {
			return;
		}
		
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();

			sql = "select * from accounts";
			//select命令の準備
			ps = con.prepareStatement(sql);
			//select命令を実行
			rs = ps.executeQuery();
			
			List<Accounts> list = new ArrayList<>();

			while(rs.next()) {
				Accounts accounts = new Accounts(rs.getInt("account_id"),
							rs.getString("name"),
							rs.getString("mail"),
							rs.getString("password"),
							rs.getInt("authority")
						);

				list.add(accounts);
			}
			
			req.setAttribute("list", list);

			//JSPへフォワード
			getServletContext().getRequestDispatcher("/WEB-INF/s0041.jsp")
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
}
