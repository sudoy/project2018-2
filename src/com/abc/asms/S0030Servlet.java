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

import com.abc.asms.beans.InsertAccounts;
import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0030.html")
public class S0030Servlet extends HttpServlet {
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

		HttpSession session = req.getSession();

		//セッションをnullにする
		session.setAttribute("ia", null);

		getServletContext().getRequestDispatcher("/WEB-INF/s0030.jsp")
			.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		//キャンセル時の入力保持
		if(req.getParameter("cancel") != null) {
			InsertAccounts ia = (InsertAccounts)session.getAttribute("ia");
			session.setAttribute("ia", ia);

			getServletContext().getRequestDispatcher("/WEB-INF/s0030.jsp")
				.forward(req, resp);
			return;
		}

		String accountId = req.getParameter("accountId");
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");
		String authority1 = req.getParameter("authority1");
		String authority2 = req.getParameter("authority2");

		InsertAccounts ia = new InsertAccounts(name,mail,password1,password2,authority1,authority2);
		session.setAttribute("ia", ia);

		//バリデーションチェック(メールアドレス重複チェック以外)
		List<String> errors = validate(accountId, name, mail,
				password1, password2, authority1, authority2);

		if (errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/s0030.jsp")
				.forward(req, resp);

			session.setAttribute("ia", null);

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

			//sqlが実行出来たらエラー（そのメールアドレスが既に存在している）　→　s0030に返す
			if(rs.next()) {
				errors.add("メールアドレスが既に登録されています。");
				session.setAttribute("errors", errors);
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

		resp.sendRedirect("S0031.html");

	}

	private List<String> validate(String accountId, String name,
				String mail,String password1, String password2,
				String authority1, String authority2) {

		List<String> errors = new ArrayList<>();

		//氏名必須入力チェック
		if (name.equals("")){
			errors.add("氏名を入力して下さい。");
		}

		//氏名長さチェック
		if (name.length() >= 21){
			errors.add("氏名が長すぎます。");
		}

		//メールアドレス必須チェック
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

		//パスワード必須入力チェック
		if (password1.equals("")){
			errors.add("パスワードを入力して下さい。");
		}

		//パスワード長さチェック
		if (password1.length() >= 31){
			errors.add("パスワードが長すぎます。");
		}

		//パスワード(確認)必須入力チェック
		if (password2.equals("")){
			errors.add("パスワード(確認)を入力して下さい。");
		}

		//パスワード等値チェック
		if (!password1.equals(password2)){
			errors.add("パスワードとパスワード（確認）が一致していません。");
		}

		//売上登録権限必須チェック
		if (authority1 == null){
			errors.add("売上登録権限を入力して下さい。");
		}

		//売上登録権限値チェック
		if (!(authority1 == null) && !authority1.equals("0") && !authority1.equals("1")){
			errors.add("売上登録権限に正しい値を入力して下さい。");
		}

		//アカウント登録権限必須チェック
		if (authority2 == null){
			errors.add("アカウント登録権限を入力して下さい。");
		}

		//アカウント登録権限必須チェック
		if (!(authority2 == null) && !authority2.equals("0") && !authority2.equals("1")){
			errors.add("アカウント登録権限に正しい値を入力して下さい。");
		}

		return errors;

	}
}
