package com.abc.asms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/S0021.html")
public class S0021Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// ログインチェック
//		if(!HtmlUtils.checkLogin(req, resp)) {
//			return;
//		}

		//foward
		getServletContext().getRequestDispatcher("/WEB-INF/s0021.jsp")
			.forward(req, resp);


	}


}
