package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/S0043.html")
public class S0043Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		String accountId = req.getParameter("account_id");
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String passwordc = req.getParameter("passwordc");
		String authority1 = req.getParameter("authority1");
		String authority2 = req.getParameter("authority2");
		String authority = authority1 + authority2;
		
		req.setAttribute("name", name);
		req.setAttribute("mail", mail);
		req.setAttribute("password", password);
		req.setAttribute("passwordc", passwordc);
		req.setAttribute("authority1", authority1);
		req.setAttribute("authority2", authority2);
		req.setAttribute("authority", authority);
		
		getServletContext().getRequestDispatcher("/WEB-INF/s0043.jsp")
		.forward(req, resp);
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
		String passwordc = req.getParameter("passwordc");
		String authority1 = req.getParameter("authority1");
		String authority2 = req.getParameter("authority2");
		String authority = authority1 + authority2;
		
		//バリデーションチェック
//				List<String> errors = validate(accountId, name, mail, password, passwordc, authority1, authority2);
//				if (errors.size() > 0) {
//					session.setAttribute("errors", errors);
//					getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
//					return;
//				}

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = com.abc.asms.utils.DBUtils.getConnection();

			sql = "update accounts set name = ?, mail = ?, password = ?, authority = ? where account_id = ?;";
			ps = con.prepareStatement(sql);

			//insert命令にポストデータの内容をセット
			ps.setString(1, name);
			ps.setString(2, mail);
			ps.setString(3, password);
			ps.setString(4, authority);
			ps.setString(5, accountId);

			
			System.out.println(ps);
			
			ps.executeUpdate();
			
			//成功メッセージ
//			List<String> successes = new ArrayList<>();
//			successes.add("更新しました。");
//			session.setAttribute("success", successes);


			resp.sendRedirect("S0041.html");
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
