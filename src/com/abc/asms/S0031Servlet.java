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

import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0031.html")
public class S0031Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}

		//権限チェック
		if(!AuthorityUtils.checkSalesAuthority(req, resp)) {
			return;
		}

		getServletContext().getRequestDispatcher("/WEB-INF/s0031.jsp")
			.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		String accountId = req.getParameter("accountId");
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String authority1 = req.getParameter("authority1");
		String authority2 = req.getParameter("authority2");
		String authority = authority1 + authority2;

		if (authority.equals("00")) {
			authority = "0";
		}

		if (authority.equals("10")) {
			authority = "1";
		}

		if(authority.equals("01")) {
			authority = "10";
		}

		List<String> successes = new ArrayList<>();
		successes.add("No" + accountId + "のアカウントを登録しました。");
		session.setAttribute("successes", successes);

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = DBUtils.getConnection();

			//INSERT文（パスワードはMD5でハッシュ化）
			sql = "INSERT INTO accounts(name, mail, password, authority)"
					+ "VALUES(?,?,MD5(?),?);";

			ps = con.prepareStatement(sql);

			ps.setString(1, name);
			ps.setString(2, mail);
			ps.setString(3, password);
			ps.setString(4, authority);

			ps.executeUpdate();

		}catch(Exception e){
			throw new ServletException(e);

		}finally{
			try{
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){}
		}

		resp.sendRedirect("S0030.html");

	}
}
