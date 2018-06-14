package com.abc.asms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/S0045.html")
public class S0045Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher("/WEB-INF/s0045.jsp")
				.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();

		String mail = req.getParameter("mail");
		req.setAttribute("mail", mail);
		//バリデーションチェック
		List<String> errors = validate(mail);
		if (errors.size() > 0) {
			session.setAttribute("errors", errors);
			//req.setAttribute("errors", errors);
			resp.sendRedirect("S0045.html");
			return;
		}
		
		//メール送信機能
		

		//成功メッセージ
		List<String> successes = new ArrayList<>();
		successes.add("パスワード再設定メールを送信しました。");
		session.setAttribute("successes", successes);

		getServletContext().getRequestDispatcher("/WEB-INF/s0045.jsp")
				.forward(req, resp);

	}

	private List<String> validate(String mail) {
		List<String> errors = new ArrayList<>();
		//1-1メールアドレスの必須入力
		if (mail.equals("")) {
			errors.add("メールアドレスを入力してください。");
		}
		//1-2メールアドレス長さチェック
		if (mail.length() > 101) {
			errors.add("メールアドレスが長すぎます。");
		}
		//1-3メールアドレス形式チェック
		if (!mail.contains("@")) {
			errors.add("メールアドレスの形式が間違っています。");
		}
		//1-4アカウントテーブル存在チェック
		if (!mail.contains("@")) {
			errors.add("メールアドレスを正しく入力してください。");
		}		//1-3メールアドレス形式チェック
		//1-5メール送信エラー
//		if ()) {
//			errors.add("予期しないエラーが発生しました。");
//		}

		return errors;
	}
}
