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

import com.abc.asms.utils.DBUtils;

@WebServlet("/S0046.html")
public class S0046Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		String user = req.getParameter("user");

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		//バリデーションチェック
		List<String> errors = validate1(user);
		if (errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/s0046.jsp")
					.forward(req, resp);
			return;
		}

		try {
			con = DBUtils.getConnection();

			sql = "SELECT * FROM accounts WHERE mail = ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, user);

			rs = ps.executeQuery();

			//1-1メールアドレス存在チェック
			if (!rs.next()) {
				errors.add("メールアドレスが存在しません。");
				session.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/s0046.jsp")
						.forward(req, resp);
				return;
			}

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
		String user = req.getParameter("user");
		req.setAttribute("user", user);

		//バリデーションチェック
		List<String> errors = validate(password1, password2);
		if (errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/s0046.jsp")
					.forward(req, resp);
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;

		try {
			con = DBUtils.getConnection();

			//1-1メールアドレス存在確認チェック
			sql = "SELECT * FROM accounts WHERE mail = ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, user);

			rs = ps.executeQuery();

			if (!rs.next()) {
				errors.add("メールアドレスが存在しません。");
				session.setAttribute("errors", errors);
				resp.sendRedirect("S0046.html");
				return;
			}
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

		try {
			con = DBUtils.getConnection();

			sql = "update accounts set password = MD5(?) where mail = ?";
			ps = con.prepareStatement(sql);

			//insert命令にポストデータの内容をセット
			ps.setString(1, password1);
			ps.setString(2, user);
			ps.executeUpdate();
			List<String> successes = new ArrayList<>();
			successes.add("パスワードを再設定しました。");
			session.setAttribute("successes", successes);

			resp.sendRedirect("C0010.html");

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	private List<String> validate1(String user) {
		List<String> errors = new ArrayList<>();
		return errors;
	}

	private List<String> validate(String password1, String password2) {
		List<String> errors = new ArrayList<>();

		//1-2新パスワードの必須入力
		if (password1.equals("")) {
			errors.add("パスワードを入力して下さい");
		}
		//1-3メールアドレス長さチェック
		if (password1.length() >= 31) {
			errors.add("パスワードが長すぎます。");
		}
		//1-4新パスワード確認の必須入力
		if (password2.equals("")) {
			errors.add("確認用パスワードを入力して下さい");
		}
		//1-5新パスワード一致チェック
		if (!password1.equals(password2)) {
			errors.add("新パスワードとパスワード（確認）が一致していません。");
		}
		return errors;
	}
}
