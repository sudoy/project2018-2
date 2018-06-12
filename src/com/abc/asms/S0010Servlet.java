package com.abc.asms;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

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

		List<String> errors =  validate(saleDate, accountId, categoryId, tradeName, unitPrice, saleNumber);
		if(errors.size() > 0) {
			req.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp")
				.forward(req, resp);
			return;
		}




	}

	private List<String> validate(String saleDate, String accountId, String categoryId, String tradeName, String unitPrice, String saleNumber) {
		List<String> errors = new ArrayList<>();

		//必須入力
		if(saleDate.equals("")){
			errors.add("販売日は必須入力です。");
		}

		if(accountId.equals("")) {
			errors.add("担当は必須入力です。");
		}

		if(categoryId.equals("")) {
			errors.add("カテゴリーは必須入力です。");
		}

		if(tradeName.equals("")) {
			errors.add("商品名は必須入力です。");
		}

		if(unitPrice.equals("")) {
			errors.add("単価は必須入力です。");
		}

		if(saleNumber.equals("")) {
			errors.add("個数は必須入力です。");
		}

		//販売日の形式（yyyy/MM//dd）
		if(!saleDate.equals("")) {
			try {
				LocalDate.parse(saleDate, DateTimeFormatter.ofPattern("uuuu/MM/dd")
					.withResolverStyle(ResolverStyle.STRICT));
			}catch(Exception e) {
				errors.add("期限は「YYYY/MM/DD」形式で入力して下さい。");
			}
		}

		//単価数字のみにする
		if(!unitPrice.equals("")) {
		    try {
		        Integer.parseInt(unitPrice);
		    } catch (NumberFormatException e) {
		        errors.add("単価は数字で入力してください。");
		    }
		}

		//個数数字のみにする
		if(!saleNumber.equals("")) {
		    try {
		        Integer.parseInt(saleNumber);
		    } catch (NumberFormatException e) {
		        errors.add("個数は数字で入力してください。");
		    }
		}
		return errors;

	}

}
