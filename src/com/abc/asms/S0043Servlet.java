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
import com.abc.asms.utils.Utils;

@WebServlet("/S0043.html")
public class S0043Servlet extends HttpServlet {
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

			req.setCharacterEncoding("utf-8");

			HttpSession session = req.getSession();

			String accountId = req.getParameter("account_id");
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

			Connection con = null;
			PreparedStatement ps = null;
			String sql = null;

			try {
				con = com.abc.asms.utils.DBUtils.getConnection();

				if (password.equals("")) {
					sql = "update accounts set name = ?, mail = ?, authority = ? where account_id = ?;";
					ps = con.prepareStatement(sql);

					//insert命令にポストデータの内容をセット
					ps.setString(1, name);
					ps.setString(2, mail);
					ps.setString(3, authority);
					ps.setString(4, accountId);

					ps.executeUpdate();

					//成功メッセージ
					List<String> successes = new ArrayList<>();
					successes.add("No" + accountId + "のアカウントを更新しました。");
					session.setAttribute("successes", successes);

					resp.sendRedirect("S0041.html");

				} else {

					sql = "update accounts set name = ?, mail = ?, password = MD5(?), authority = ? where account_id = ?;";
					ps = con.prepareStatement(sql);

					//insert命令にポストデータの内容をセット
					ps.setString(1, name);
					ps.setString(2, mail);
					ps.setString(3, password);
					ps.setString(4, authority);
					ps.setString(5, accountId);

					System.out.println(ps);

					ps.executeUpdate();

					//成功メッセージ
					List<String> successes = new ArrayList<>();
					successes.add("No" + accountId + "のアカウントを更新しました。");
					session.setAttribute("successes", successes);

					resp.sendRedirect("S0041.html");
				}

			} catch (Exception e) {
				throw new ServletException(e);
			} finally {
				try {
					if (con != null) {
						con.close();
					}
					if (ps != null) {
						ps.close();
					}
				} catch (Exception e) {
				}
			}
		}
	}
