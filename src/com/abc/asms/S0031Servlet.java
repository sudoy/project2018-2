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

import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0031.html")
public class S0031Servlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}

		//権限チェック
		if(!AuthorityUtils.checkAccountEditAuthority(req, resp)) {
			return;
		}


		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String password1 = req.getParameter("password1");
		String authority1 = req.getParameter("authority1");
		String authority2 = req.getParameter("authority2");
		String authority = authority2 + authority1;

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		int id = 0;

		try {
			con = DBUtils.getConnection();

			//INSERT文（パスワードはMD5でハッシュ化）
			sql = "INSERT INTO accounts(name, mail, password, authority)"
					+ "VALUES(?,?,MD5(?),?);";

			ps = con.prepareStatement(sql);

			ps.setString(1, name);
			ps.setString(2, mail);
			ps.setString(3, password1);
			ps.setString(4, authority);

			ps.executeUpdate();

			try{
				DBUtils.close(ps);
			}catch(Exception e){}

			//insertされた後のidをselect文で取り出す
			sql = "SELECT LAST_INSERT_ID() as id FROM accounts";

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			if(rs.next()) {
				//sqlからidを取り出してsetAttribute
				id = rs.getInt("id");

				//成功メッセージ（遷移先で出る）
				List<String> successes = new ArrayList<>();
				successes.add("No" + id + "のアカウントを登録しました。");
				session.setAttribute("successes", successes);
			}

		}catch(Exception e){
			throw new ServletException(e);

		}finally{
			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){}
		}

		//登録処理後、登録画面に遷移
		resp.sendRedirect("S0030.html");

	}
}
