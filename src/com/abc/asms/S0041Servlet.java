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
import com.abc.asms.beans.SearchKeepA;
import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}
		//権限チェック
		if (!AuthorityUtils.hideBottun(req, resp)) {
			getServletContext().getRequestDispatcher("/WEB-INF/s0041a.jsp")
					.forward(req, resp);
		}
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> errors = new ArrayList<>();
		if (errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/s0020.jsp")
					.forward(req, resp);
			return;
		}
		try {
			con = DBUtils.getConnection();
			//SQL
			sql = "select account_id,name,mail,password,authority from accounts where 0 = 0 ";
			SearchKeepA sa = (SearchKeepA) session.getAttribute("sa");
			//氏名検索
			if (sa.getAccountName() != "") {
				sql += " and name like '%" + sa.getAccountName() + "%'";
			} else {
				sql += "";
			}

			//メール検索
			if (sa.getMail() != "") {
				sql += " and mail like '%" + sa.getMail() + "%'";
			} else {
				sql += "";
			}

			//権限検索
			if (sa.getSaleAuthority().equals("no") && sa.getAccountAuthority().equals("no")) {
				sql += " and authority = 0";
			} else if (sa.getSaleAuthority().equals("ok") && sa.getAccountAuthority().equals("no")) {
				sql += " and authority = 1";
			} else if (sa.getSaleAuthority().equals("no") && sa.getAccountAuthority().equals("ok")) {
				sql += " and authority = 10";
			} else if (sa.getSaleAuthority().equals("ok") && sa.getAccountAuthority().equals("ok")) {
				sql += " and authority = 11";
			} else if (sa.getSaleAuthority().equals("all") && sa.getAccountAuthority().equals("all")) {
				sql += " and authority in(0,1,10,11)";
			} else if (sa.getSaleAuthority().equals("all") && sa.getAccountAuthority().equals("no")) {
				sql += " and authority in(0,1,10)";
			} else if (sa.getSaleAuthority().equals("all") && sa.getAccountAuthority().equals("ok")) {
				sql += " and authority in(0,10,11)";
			} else if (sa.getSaleAuthority().equals("no") && sa.getAccountAuthority().equals("all")) {
				sql += " and authority in(0,1,10)";
			} else if (sa.getSaleAuthority().equals("ok") && sa.getAccountAuthority().equals("all")) {
				sql += " and authority in(0,1,11)";
			}

			//SELECT命令の準備
			ps = con.prepareStatement(sql);
			//SELECT命令を実行
			rs = ps.executeQuery();
			List<Accounts> list = new ArrayList<>();
			while (rs.next()) {
				Accounts account = new Accounts(rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getInt("authority"));

				list.add(account);
			}
			if (list.isEmpty()) {
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
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			//終了処理
			try {
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			} catch (Exception e) {
			}
		}
	}

}
