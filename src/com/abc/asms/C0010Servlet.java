package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.beans.Accounts;
import com.abc.asms.utils.DBUtils;

@WebServlet("/C0010.html")
public class C0010Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/c0010.jsp")
				.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("mail");
		String password = req.getParameter("password");

		List<String> errors = validate(email, password);
		if (errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/c0010.jsp")
					.forward(req, resp);
			return;
		}
		//関連チェック
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			//データベースの接続を確立
			con = DBUtils.getConnection();
			//GETパラメーターを取得
			sql = "SELECT account_id, name, mail, password,authority from accounts where mail = ? and password = MD5(?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			//SELCT命令を実行
			rs = ps.executeQuery();

			if (!rs.next()) {
				errors.add("メールアドレス、パスワードを正しく入力して下さい。");
				session.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/c0010.jsp")
						.forward(req, resp);
				return;

			}
			Accounts accounts = new Accounts(rs.getInt("account_id"),
					rs.getString("name"),
					rs.getString("mail"),
					rs.getString("password"),
					rs.getInt("authority"));
			//session にログイン情報を保存する。
			session.setAttribute("accounts", accounts);
			resp.sendRedirect("C0020.html");

		} catch (Exception e) {
			throw new ServletException(e);

		} finally {
			try {
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			} catch (Exception e) {
			}
		}
	}

	private List<String> validate(String email, String password) {
		List<String> errors = new ArrayList<>();
		//1-1
		if (email.equals("")) {
			errors.add("メールアドレスを入力して下さい。");
		}
		//1-2
		if (email.length() > 100) {
			errors.add("メールアドレスが長すぎます");
		}
		//1-3
		if(!email.equals("") && email.contains("@")) {
			String pattern = "^[a-zA-Z0-9]*$";
			Pattern p = Pattern.compile(pattern);
			String[] split = email.split("@", -1);
			String firstCharacter = email.substring(0, 1);
			if(!p.matcher(firstCharacter).find()){
				errors.add("メールアドレスを正しく入力して下さい。");
			}else if(!split[0].matches("^[a-zA-Z0-9._-]*$") || split[0].length() == 1) {
				errors.add("メールアドレスを正しく入力して下さい。");
			}else if(!split[0].matches("^[a-zA-Z0-9._-]*$") || split[0].length() == 0) {
				errors.add("メールアドレスを正しく入力して下さい。");
			}else if(!split[1].matches("^[a-zA-Z0-9._-]*$") ||split[0].length() == 0) {
				errors.add("メールアドレスを正しく入力して下さい。");
			}else if(!split[1].contains(".") ||split[0].length() == 0) {
				errors.add("メールアドレスを正しく入力して下さい。");
			}else if(!split[0].matches("^[a-zA-Z0-9._-]*$") && split[0].length() == 0) {
				errors.add("メールアドレスを正しく入力して下さい。");
			}
		}
		//1-4
		if (password.equals("")) {
			errors.add("パスワードが未入力です。");
		}
		//1-5
		if (password.length() > 30) {
			errors.add("パスワードが長すぎます");
		}


		return errors;

	}

}
