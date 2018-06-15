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
import com.abc.asms.utils.HtmlUtils;

@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//ログインチェック
		if (!HtmlUtils.checkLogin(req, resp)) {
			return;
		}


		//権限チェック
		if(!AuthorityUtils.tabDeleteAccountAuthority(req, resp)) {
			getServletContext().getRequestDispatcher("/WEB-INF/s0041a.jsp")
			.forward(req, resp);
		}

		//JSPへフォワード
		getServletContext().getRequestDispatcher("/WEB-INF/s0041.jsp")
		.forward(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String sauthority = req.getParameter("sauthority");
		String aauthority = req.getParameter("aauthority");
		List<String> errors =  validate(name,mail);
		if(errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/s0040.jsp")
			.forward(req, resp);
			return;
		}
		try{
			con = DBUtils.getConnection();

			//SQL
			sql = "select * from accounts where 0 = 0 ";
			//氏名検索
			if(name != "") {
				sql += " and name like '%" + name + "%'";
			}else {
				sql += "";
			}

			//メール検索
			if(mail != "") {
				sql += " and mail like '%" + mail + "%'";
			}else {
				sql += "";
			}

			//権限検索
			if(sauthority.equals("no") && aauthority.equals("no")) {
				sql += " and authority = 0";
			}else if(sauthority.equals("ok") && aauthority.equals("no")) {
				sql += " and authority = 1";
			}else if(sauthority.equals("no") && aauthority.equals("ok")) {
				sql += " and authority = 10";
			}else if(sauthority.equals("ok") && aauthority.equals("ok")) {
				sql += " and authority = 11";
			}else if(sauthority.equals("all") && aauthority.equals("all")) {
				sql += " and authority in(0,1,10,11)";
			}else if(sauthority.equals("all") && aauthority.equals("no")) {
				sql += " and authority in(0,1,10)";
			}else if(sauthority.equals("all") && aauthority.equals("ok")) {
				sql += " and authority in(0,10,11)";
			}else if(sauthority.equals("no") && aauthority.equals("all")) {
				sql += " and authority in(0,1,10)";
			}else if(sauthority.equals("ok") && aauthority.equals("all")) {
				sql += " and authority in(0,1,11)";
			}

			//SELECT命令の準備
			ps = con.prepareStatement(sql);
			System.out.println(ps);
			//SELECT命令を実行
			rs = ps.executeQuery();


			List<Accounts> list = new ArrayList<>();
			while(rs.next()) {
				Accounts account = new Accounts(rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getInt("authority"));

				list.add(account);
			}
			if(list.isEmpty()) {
				errors = new ArrayList<>();
				errors.add("検索結果はありません。");
				session.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/s0040.jsp")
						.forward(req, resp);
				return;
			}
			//JavaBeansをJSPへ渡す
			req.setAttribute("list", list);

			//foward
			getServletContext().getRequestDispatcher("/WEB-INF/s0041.jsp")
				.forward(req, resp);
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			//終了処理
			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){
			}
		}

	}
	private List<String> validate(String name, String mail) {
		List<String> errors = new ArrayList<>();


		if(name.length() > 20) {
			errors.add("氏名の指定が長すぎます。");
		}

		if(mail.length() > 100) {
			errors.add("メールアドレスの指定が長すぎます。");
		}
//		if (!mail.contains("@")) {
//			errors.add("メールアドレスの形式が誤っています。");
//		}
		return errors;



	}
}
