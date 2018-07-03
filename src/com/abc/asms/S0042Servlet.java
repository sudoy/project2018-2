package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.abc.asms.beans.EditAccounts;
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
		if (!AuthorityUtils.checkAccountEditAuthority(req, resp)) {
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

			sql = "select account_id, name, mail, password, authority from accounts where account_id = ?";
			//select命令の準備
			ps = con.prepareStatement(sql);

			//select文にパラメータの内容をセット
			ps.setString(1, req.getParameter("account_id"));

			//select命令を実行
			rs = ps.executeQuery();

			rs.next();

			if(req.getParameter("account_id") == null) {
				List<String> errors = new ArrayList<>();
				errors.add("不正なアクセスです。");
				session.setAttribute("errors", errors);
				resp.sendRedirect("S0040.html");
				return ;
			}

			int accountId = rs.getInt("account_id");
			String name = rs.getString("name");
			String mail = rs.getString("mail");
			String password = rs.getString("password");
			int authority = rs.getInt("authority");

			Accounts accountsList = new Accounts(accountId, name, mail, password, authority);
			req.setAttribute("accountsList", accountsList);

		} catch (Exception e) {
			e.printStackTrace();
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

		//JSPへフォワード
		getServletContext().getRequestDispatcher("/WEB-INF/s0042.jsp")
				.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}

		//権限チェック
		if (!AuthorityUtils.checkAccountEditAuthority(req, resp)) {
			return;
		}

		req.setCharacterEncoding("utf-8");

		HttpSession session = req.getSession();

		String accountId = req.getParameter("account_id");
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String passwordc = req.getParameter("passwordc");
		String authority1 = req.getParameter("authority1");
		String authority2 = req.getParameter("authority2");

		EditAccounts ea = new EditAccounts(accountId,name,mail,password,passwordc,authority1,authority2);
		session.setAttribute("ea", ea);

		//バリデーションチェック
		List<String> errors = validate(accountId, name, mail, password, passwordc, authority1, authority2);
		if (errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/s0042.jsp")
					.forward(req, resp);
			return;
		}

		//メールアドレス重複チェック
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql;

		DBUtils.getCategoriesAndAccounts(req, resp);

		try {
			con = DBUtils.getConnection();

			sql = "select account_id from accounts where mail = ? ";

			ps = con.prepareStatement(sql);

			ps.setString(1, mail);

			rs = ps.executeQuery();

			rs.next();

			if(rs.next()) {
				errors.add("メールアドレスが既に登録されています。");
				session.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/s0042.jsp")
						.forward(req, resp);
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		resp.sendRedirect("S0043.html?account_id=" + accountId);
	}

	private List<String> validate(String accountId, String name, String mail, String password, String passwordc,
			String authority1, String authority2) {
		List<String> errors = new ArrayList<>();
		//1-1氏名の必須入力
		if (name.equals("")) {
			errors.add("氏名を入力して下さい。");
		}
		//1-2氏名の長さチェック
		if (name.length() >= 21) {
			errors.add("氏名が長すぎます。");
		}
		//1-1
		if (mail.equals("")) {
			errors.add("メールアドレスを入力して下さい。");
			return errors;
		}
		//1-2
		if (mail.length() > 100) {
			errors.add("メールアドレスが長すぎます");
		}
		//1-3
		if(!mail.contains("@")) {
			errors.add("メールアドレスを正しく入力して下さい。");
		}
		if(!mail.equals("") && mail.contains("@")) {
			String pattern = "^[a-zA-Z0-9]*$";
			Pattern p = Pattern.compile(pattern);
			String[] split = mail.split("@", -1);
			String firstCharacter = mail.substring(0, 1);
			if(!p.matcher(firstCharacter).find()){
				errors.add("メールアドレスを正しく入力して下さい。");
			}else if(!split[0].matches("^[a-zA-Z0-9._-]*$") ) {
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

		//1-7パスワードの長さチェック
		if (password.length() >= 31) {
			errors.add("パスワードが長すぎます。");
		}
		//1-9パスワード一致チェック
		if (!password.equals(passwordc)) {
			errors.add("パスワードとパスワード（確認）が一致していません。");
		}

		//1-10,11 売上登録権限必須入力チェック
		if(authority1 == null) {
			errors.add("売上登録権限を入力してください。");
		}else if(!authority1.equals("0") && !authority1.equals("1")) {
			errors.add("売上登録権限に正しい値を入力してください。");
		}

		//1-12,13アカウント登録権限必須チェック
		if (authority2 == null) {
			errors.add("アカウント登録権限を入力して下さい。");
		}else if(!authority2.equals("0") && !authority2.equals("10")) {
			errors.add("アカウント登録権限に正しい値を入力して下さい。");
		}

		return errors;

	}
}
