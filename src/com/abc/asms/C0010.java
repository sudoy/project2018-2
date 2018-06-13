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
import com.abc.asms.utils.DBUtils;


@WebServlet("/C0010.html")
public class C0010 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
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
		List<String> errors = validate(email,password);
		if(errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/c0010.jsp").forward(req, resp);
			return;
		}
		//関連チェック
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();
			//GETパラメーターを取得
			sql = "SELECT account_id, name, mail, password,authority from accounts where mail = ? and password = MD5(?)";
			ps= con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			//SELCT命令を実行
			rs=ps.executeQuery();

			if(rs.next()) {
				//email passwordが正しいとき
				//ログイン処理
				Accounts accounts = new Accounts(rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getInt("authority"));
				session.setAttribute("accounts", accounts);
				resp.sendRedirect("C0020.html");


			}else {
				errors.add("メールアドレスかパスワードが間違っています。");
				session.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/c0010.jsp").forward(req, resp);
			}

		}catch(Exception e){
			throw new ServletException(e);

		}finally{
			try{
				if(con != null){con.close();}
				if(ps != null){ps.close();}
				if(rs != null){rs.close();}
			}catch(Exception e){}
		}
	}
	private List<String> validate(String email,String password) {
		List<String> errors = new ArrayList<>();

		if(email.equals("")) {
			errors.add("メールアドレスを入力してください。");
		}
		if(email.length() > 100) {
			errors.add("メールアドレスが長すぎます");
		}

		if(password.equals("")) {
			errors.add("パスワードが未入力です。");
		}
		if(password.length() > 30) {
			errors.add("パスワードが長すぎます");
		}
		return errors;

	}

}
