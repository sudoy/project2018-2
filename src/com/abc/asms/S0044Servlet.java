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


@WebServlet("/S0044.html")
public class S0044Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();
			
		sql = "select * from accounts where account_id = ?";
		//select命令の準備
		ps = con.prepareStatement(sql);

		//select文にパラメータの内容をセット
		ps.setString(1, req.getParameter("account_id"));

		//select命令を実行
		rs = ps.executeQuery();
		
		rs.next();

		int accountId = rs.getInt("account_id");
		String name = rs.getString("name");
		String mail = rs.getString("mail");
		String password = rs.getString("password");
		int authority = rs.getInt("authority");

		 Accounts accounts = new Accounts(accountId, name,  mail,  password, authority);
		req.setAttribute("accounts", accounts);

		//JSPへフォワード
		getServletContext().getRequestDispatcher("/WEB-INF/s0044.jsp")
				.forward(req, resp);

	} catch (Exception e) {
		throw new ServletException(e);
	} finally {
		try {
			if (rs != null) {
				con.close();
			}
			if (ps != null) {
				ps.close();
			}
			DBUtils.close(con);
		} catch (Exception e) {
		}
	}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String accountId = req.getParameter("account_id");
		
		//バリデーションチェック
//		List<String> errors = validate(id);
//		if (errors.size() > 0) {
//			session.setAttribute("errors", errors);
//			resp.sendRedirect("index.html");
//			return;
//		}
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();

			sql = "delete from accounts where account_id = ?";
			//select命令の準備
			ps = con.prepareStatement(sql);

			//select文にパラメータの内容をセット
			ps.setString(1, accountId);

			//select命令を実行
			ps.executeUpdate();
			
			//成功メッセージ
			
			List<String> successes = new ArrayList<>();
			successes.add("No" + accountId + "のアカウントを削除しました。");
			session.setAttribute("successes", successes);

			resp.sendRedirect("S0041.html");

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				if (rs != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
				DBUtils.close(con);
			} catch (Exception e) {
			}
		}
	}
//	private List<String> validate(String id) {
//		List<String> errors = new ArrayList<>();
//		
//		//日付の必須入力
//		if (id == null || id.equals("")) {
//			errors.add("不正なアクセスです。");
//		}
//		return errors;
//	}
	}
