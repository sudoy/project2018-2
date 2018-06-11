package com.abc.asms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//foward→index.jsp
		getServletContext().getRequestDispatcher("/WEB-INF/s0010.jsp")
			.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String saleDate = req.getParameter("saleDate");
		String accountId = req.getParameter("accountId");
		String categoryId = req.getParameter("categoryId");
		String tradeName = req.getParameter("tradeName");
		String unitPrice = req.getParameter("unitPrice");
		String saleNumber = req.getParameter("saleNumber");
		String note = req.getParameter("note");

		req.setAttribute("saleDate", saleDate);
		req.setAttribute("accountId", accountId);
		req.setAttribute("categoryId", categoryId);
		req.setAttribute("tradeName", tradeName);
		req.setAttribute("unitPrice", unitPrice);
		req.setAttribute("saleNumber", saleNumber);
		req.setAttribute("note", note);

		//JSPへフォワード
		getServletContext().getRequestDispatcher("/WEB-INF/s0011.jsp")
			.forward(req, resp);

	}

}
