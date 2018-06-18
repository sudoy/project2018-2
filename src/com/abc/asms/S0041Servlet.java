package com.abc.asms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.asms.utils.AuthorityUtils;
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

}
