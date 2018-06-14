package com.abc.asms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/S0030.html")
public class S0030Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/s0030.jsp")
			.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String accountId = req.getParameter("accountId");
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		String authority1 = req.getParameter("authority1");
		String authority2 = req.getParameter("authority2");
		String authority = authority1 + authority2;

		req.setAttribute("accountId", accountId);
		req.setAttribute("name", name);
		req.setAttribute("mail", mail);
		req.setAttribute("password", password);
		req.setAttribute("password2", password2);
		req.setAttribute("authority1", authority1);
		req.setAttribute("authority2", authority2);
		req.setAttribute("authority", authority);

		getServletContext().getRequestDispatcher("/WEB-INF/s0031.jsp")
			.forward(req, resp);

	}

}
