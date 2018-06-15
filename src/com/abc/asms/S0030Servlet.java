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

import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.HtmlUtils;

@WebServlet("/S0030.html")
public class S0030Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//ログインチェック
		if (!HtmlUtils.checkLogin(req, resp)) {
			return;
		}

		//権限チェック
		if(!AuthorityUtils.checkSalesAuthority(req, resp)) {
			return;
		}

		getServletContext().getRequestDispatcher("/WEB-INF/s0030.jsp")
			.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String accountId = req.getParameter("accountId");
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		String authority1 = req.getParameter("authority1");
		String authority2 = req.getParameter("authority2");
		String authority = authority1 + authority2;

		req.setAttribute("accountId", accountId);
		req.setAttribute("name", name);
		req.setAttribute("mail", mail);
		req.setAttribute("password", password);
		req.setAttribute("password2", password2);
		req.setAttribute("authority1", authority1);
		req.setAttribute("authority2", authority2);
		req.setAttribute("authority", authority);

		//バリデーションチェック(メールアドレス重複チェック以外)
		List<String> errors = validate(accountId, name, mail,
				password, password2, authority1, authority2);

		if (errors.size() > 0) {
			req.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/s0030.jsp")
				.forward(req, resp);
			return;
		}

		//メールアドレス重複チェック
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try{
			con = DBUtils.getConnection();

			sql = "SELECT mail FROM accounts WHERE mail = ?;";

			ps = con.prepareStatement(sql);

			ps.setString(1, mail);

			rs = ps.executeQuery();

			//sqlが実行出来たらエラー　→　s0030に返す
			if(rs.next()) {
				errors.add("メールアドレスが既に登録されています。");
				req.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/s0030.jsp")
					.forward(req, resp);
				return;
			}

		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){
			}
		}

		getServletContext().getRequestDispatcher("/WEB-INF/s0031.jsp")
			.forward(req, resp);

	}

	private List<String> validate(String accountId, String name,
				String mail,String password, String password2,
				String authority1, String authority2) {

		List<String> errors = new ArrayList<>();

		//氏名必須入力チェック
		if (name.equals("")) {
			errors.add("氏名を入力してください。");
		}

		//氏名長さチェック
		if (name.length() > 21) {
			errors.add("氏名が長すぎます。");
		}

		//メールアドレス必須チェック
		if (mail.equals("")) {
			errors.add("メールアドレスを入力してください。");
		}

		//メールアドレス長さチェック
		if (mail.length() > 101) {
			errors.add("メールアドレスが長すぎます。");
		}

		//メールアドレス形式チェック
		if(!mail.equals("") && !mail.contains("@")) {
			errors.add("メールアドレスの形式が間違っています。");
		}

		//パスワード必須入力チェック
		if (password.equals("")) {
			errors.add("パスワードを入力してください。");
		}

		//パスワード長さチェック
		if (password.length() > 31) {
			errors.add("パスワードが長すぎます。");
		}

		//パスワード(確認)必須入力チェック
		if (password.equals("")) {
			errors.add("パスワード(確認)を入力してください。");
		}

		//パスワード等値チェック
		if (!password.equals(password2)) {
			errors.add("パスワードとパスワード（確認）が一致していません。");
		}

		//売上登録権限必須チェック
		if (authority1.equals("")) {
			errors.add("売上登録権限を入力してください。");
		}

		//売上登録権限値チェック
		if (!authority1.equals("0") && !authority1.equals("1")) {
			errors.add("売上登録権限に正しい値を入力してください。");
		}

		//アカウント登録権限必須チェック
		if (authority2.equals("")) {
			errors.add("アカウント登録権限を入力してください。");
		}

		//アカウント登録権限必須チェック
		if (!authority2.equals("0") && !authority2.equals("1")) {
			errors.add("アカウント登録権限に正しい値を入力してください。");
		}

		return errors;

	}
}
