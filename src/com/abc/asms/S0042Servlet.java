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
import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0042.html")
public class S0042Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}

		//権限チェック
		if(!AuthorityUtils.checkAccountEditAuthority(req, resp)) {
			return;
		}

		req.setCharacterEncoding("utf-8");
		//HttpSession session = req.getSession();
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
			getServletContext().getRequestDispatcher("/WEB-INF/s0042.jsp")
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
		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}

		//権限チェック
//		if(!AuthorityUtils.checkAccountEditAuthority(req, resp)) {
//			return;
//		}

		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();

		String accountId = req.getParameter("account_id");
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String passwordc = req.getParameter("passwordc");
		String authority1 = req.getParameter("authority1");
		String authority2 = req.getParameter("authority2");
		String authority = authority1 + authority2;

		req.setAttribute("account_id", accountId);
		req.setAttribute("name", name);
		req.setAttribute("mail", mail);
		req.setAttribute("password", password);
		req.setAttribute("passwordc", passwordc);
		req.setAttribute("authority1", authority1);
		req.setAttribute("authority2", authority2);
		req.setAttribute("authority", authority);

		//バリデーションチェック
		List<String> errors = validate(accountId, name, mail, password, passwordc, authority1, authority2);
		if (errors.size() > 0) {
			session.setAttribute("errors", errors);
			//req.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/s0042.jsp")
				.forward(req, resp);
			return;
		}

		getServletContext().getRequestDispatcher("/WEB-INF/s0043.jsp")
				.forward(req, resp);
	}

	private List<String> validate(String accountId, String name, String mail, String password, String passwordc,
			String authority1, String authority2) {
		List<String> errors = new ArrayList<>();
		//1-1氏名の必須入力
		if (name.equals("")) {
			errors.add("氏名を入力してください。");
		}
		//1-2氏名の長さチェック
		if (name.length() > 21) {
			errors.add("氏名が長すぎます。");
		}
		//1-3メールアドレス必須チェック
		if (mail.equals("")) {
			errors.add("メールアドレスを入力してください。");
		}
		//1-4メールアドレス長さチェック
		if (mail.length() > 101) {
			errors.add("メールアドレスが長すぎます。");
		}
		//1-5メールアドレス形式チェック
//		if (mail.matches("[a-z0-9A-Z]+")) {
//			errors.add("メールアドレスの形式が間違っています。");
//		}
		//1-5メールアドレス形式チェック
		if (!mail.contains("@")) {
			errors.add("メールアドレスの形式が間違っています。");
		}
		//1-6パスワードの長さtチェック
		if (password.length() > 31) {
			errors.add("パスワードが長すぎます。");
		}
		//1-7パスワード一致チェック
		if (!password.equals(passwordc)) {
			errors.add("パスワードとパスワード（確認）が一致していません。");
		}
		//1-8売上登録権限必須チェック
		if (authority1.equals("")) {
			errors.add("売上登録権限を入力してください。");
		}
		//1-9売上登録権限値チェック
		if (!authority1.equals("0") && !authority1.equals("1")) {
			errors.add("売上登録権限に正しい値を入力してください。");
		}
		//1-10アカウント登録権限必須チェック
		if (authority2.equals("")) {
			errors.add("アカウント登録権限を入力してください。");
		}
		//1-11アカウント登録権限必須チェック
		if (!authority2.equals("0") && !authority2.equals("1")) {
			errors.add("アカウント登録権限に正しい値を入力してください。");
		}
		return errors;

	}
}
