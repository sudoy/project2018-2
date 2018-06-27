package com.abc.asms;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.beans.SearchKeepSale;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;
@WebServlet("/S0020.html")
public class S0020Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// ログインチェック
		if(!Utils.checkLogin(req, resp)) {
			return;
		}
		req.setCharacterEncoding("UTF-8");
		LocalDateTime d = LocalDateTime.now();
		String today = DateTimeFormatter.ofPattern("yyyy/M/d").format(d);
		req.setAttribute("today", today);


		DBUtils.getCategoriesAndAccountsForAll(req, resp);
		getServletContext().getRequestDispatcher("/WEB-INF/s0020.jsp")
		.forward(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// ログインチェック
		if(!Utils.checkLogin(req, resp)) {
			return;
		}

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String saleDate1 = req.getParameter("sale_date1");
		String saleDate2 = req.getParameter("sale_date2");
		String accountName = req.getParameter("name");
		String[] categoryName = req.getParameterValues("category_name");
		String tradeName = req.getParameter("trade_name");
		String note = req.getParameter("note");

		DBUtils.getCategoriesAndAccountsForAll(req, resp);
		SearchKeepSale ss = new SearchKeepSale(saleDate1,saleDate2,accountName,categoryName,tradeName,note);
		List<String> errors =  validate(saleDate1,saleDate2,req,accountName);
		if(errors.size() > 0) {
			session.setAttribute("errors", errors);
			session.setAttribute("ss", ss);
			getServletContext().getRequestDispatcher("/WEB-INF/s0020.jsp")
			.forward(req, resp);
			return;
		}

		session.setAttribute("ss", ss);
		resp.sendRedirect("S0021.html");


	}
	private List<String> validate(String saleDate1,String saleDate2,HttpServletRequest req,String accountName) {
		List<String> errors = new ArrayList<>();


		if(!saleDate1.equals("")) {
			try {
				if(LocalDate.parse(saleDate1, DateTimeFormatter.ofPattern("uuuu/M/d")
						.withResolverStyle(ResolverStyle.STRICT)) != null ) {
				}else {
					LocalDate.parse(saleDate1, DateTimeFormatter.ofPattern("uuuu/MM/dd")
							.withResolverStyle(ResolverStyle.STRICT));

				}


			}catch(Exception e) {
				errors.add("販売日（検索開始日）を正しく入力して下さい。");
			}
		}

		if(!saleDate2.equals("")) {
			try {
				if(LocalDate.parse(saleDate2, DateTimeFormatter.ofPattern("uuuu/M/d")
						.withResolverStyle(ResolverStyle.STRICT)) != null ) {
					LocalDate.parse(saleDate2, DateTimeFormatter.ofPattern("uuuu/M/d")
							.withResolverStyle(ResolverStyle.STRICT)) ;
				}else {
					LocalDate.parse(saleDate2, DateTimeFormatter.ofPattern("uuuu/MM/dd")
							.withResolverStyle(ResolverStyle.STRICT));
				}

			}catch(Exception e) {
				errors.add("販売日（検索終了日）を正しく入力して下さい。");
			}
		}

		if(!saleDate2.equals("") && !saleDate1.equals("")) {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
			fmt.setLenient(false);
			try {
				Date f1 = fmt.parse(req.getParameter("sale_date1"));
				Date f2 = fmt.parse(req.getParameter("sale_date2"));
				if(f1.after(f2)) {
					errors.add("販売日（検索開始日)が販売日（検索終了日)より後の日付になっています。");
				}
			}catch(Exception e1) {

			}
		}

		return errors;



	}


}



