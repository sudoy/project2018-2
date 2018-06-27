package com.abc.asms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.beans.SearchKeepAccount;
import com.abc.asms.utils.Utils;

@WebServlet("/S0040.html")
public class S0040Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}
		HttpSession session = req.getSession();
		session.setAttribute("sa", null);

		getServletContext().getRequestDispatcher("/WEB-INF/s0040.jsp")
			.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String sauthority = req.getParameter("sauthority");
		String aauthority = req.getParameter("aauthority");
		SearchKeepAccount sa = new SearchKeepAccount(name,mail,sauthority,aauthority);
		List<String> errors =  validate(name,mail);
		if(errors.size() > 0) {
			session.setAttribute("errors", errors);
			session.setAttribute("sa", sa);
			getServletContext().getRequestDispatcher("/WEB-INF/s0040.jsp")
			.forward(req, resp);
			return;
		}

		session.setAttribute("sa", sa);
		resp.sendRedirect("S0041.html");
	}

	private List<String> validate(String name, String mail) {
		List<String> errors = new ArrayList<>();


		if(name.length() > 20) {
			errors.add("氏名の指定が長すぎます。");
		}

		if(mail.length() > 100) {
			errors.add("メールアドレスの指定が長すぎます。");
		}

		return errors;



	}
}
