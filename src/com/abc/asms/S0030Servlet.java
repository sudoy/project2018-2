package com.abc.asms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/S0030.html")
public class S0030Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

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

		List<String> errors = validate(accountId, name, mail, password, password2, authority1, authority2);
		if (errors.size() > 0) {
			req.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/s0030.jsp")
				.forward(req, resp);
			return;
		}

		getServletContext().getRequestDispatcher("/WEB-INF/s0031.jsp")
			.forward(req, resp);

	}

	private List<String> validate(String accountId, String name, String mail, String password, String password2,
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
		if(!mail.equals("") && !mail.contains("@")) {
			errors.add("メールアドレスの形式が間違っています。");
		}

		//メールアドレス重複チェック

		//パスワード必須入力チェック
		if (password.equals("")) {
			errors.add("パスワードを入力してください。");
		}
		//1-6パスワードの長さチェック
		if (password.length() > 31) {
			errors.add("パスワードが長すぎます。");
		}
		//1-7パスワード一致チェック
		if (!password.equals(password2)) {
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
