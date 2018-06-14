package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.utils.DBUtils;


@WebServlet("/S0046.html")
public class S0046Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/s0046.jsp")
				.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");
		String accountId = req.getParameter("account_id");

		//バリデーションチェック
		List<String> errors = validate(password1, password2);
		if (errors.size() > 0) {
			session.setAttribute("errors", errors);
			resp.sendRedirect("S0046.html");
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = DBUtils.getConnection();

			sql = "update accounts set password = MD5(?) where account_id = ?;";
			ps = con.prepareStatement(sql);

			//insert命令にポストデータの内容をセット
			ps.setString(1, password1);
			ps.setString(2, accountId);

			ps.executeUpdate();

			List<String> successes = new ArrayList<>();
			successes.add("パスワードを再設定しました。");
			session.setAttribute("successes", successes);

			resp.sendRedirect("C0010.html");
			
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

	private List<String> validate(String password1, String password2) {
		List<String> errors = new ArrayList<>();
		//1-1メールアドレス存在チェック
		
		//1-2新パスワードの必須入力
		if (password1.equals("")) {
			errors.add("パスワードを入力してください。");
		}
		//1-3メールアドレス長さチェック
		if (password1.length() > 31) {
			errors.add("メールアドレスが長すぎます。");
		}
		//1-4新パスワード確認の必須入力
		if (password1.equals("")) {
			errors.add("確認用パスワードを入力してください。");
		}
		//1-5アカウントテーブル存在チェック
		if (!password1.equals(password2)) {
			errors.add("新パスワードとパスワード（確認）が一致しません。");
		} 
		return errors;
	}
}
