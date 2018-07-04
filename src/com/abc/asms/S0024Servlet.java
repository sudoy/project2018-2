package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.beans.CancelBeans;
import com.abc.asms.beans.Sales;
import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0024.html")
public class S0024Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}

		//権限チェック
		if (!AuthorityUtils.checkSalesAuthority(req, resp)) {
			return;
		}

		HttpSession session = req.getSession();

		if (session.getAttribute("preserve") == null) {
			List<String> errors = new ArrayList<>();
			errors.add("不正なアクセスです。");
			session.setAttribute("errors", errors);
			resp.sendRedirect("S0020.html");
			return;
		}

		DBUtils.getCategoriesAndAccounts(req, resp);

		Sales preserve = (Sales)session.getAttribute("preserve");

		session.setAttribute("preserve", preserve);

		req.getServletContext().getRequestDispatcher("/WEB-INF/s0024.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		HttpSession session = req.getSession();

	if (req.getParameter("update") != null) {
			//ログインチェック
			if (!Utils.checkLogin(req, resp)) {
				return;
			}

			//権限チェック
			if (!AuthorityUtils.checkSalesAuthority(req, resp)) {
				return;
			}

			String id = req.getParameter("id");

			String saleDate = req.getParameter("sale_date");
			String accountId = req.getParameter("account_id");
			String categoryId = req.getParameter("category_id");
			String tradeName = req.getParameter("trade_name");
			String unitPrice = req.getParameter("unit_price");
			String saleNumber = req.getParameter("sale_number");
			String note = req.getParameter("note");

			Connection con = null;
			PreparedStatement ps = null;

			try {
				con = DBUtils.getConnection();
				String sql = ""
						+ "update sales set "
						+ "sale_date = ?, account_id = ?, category_id = ?, trade_name = ?, "
						+ "unit_price = ?, sale_number = ?, note = ? "
						+ "where sale_id = ?";

				ps = con.prepareStatement(sql);

				ps.setString(1, saleDate);
				ps.setString(2, accountId);
				ps.setString(3, categoryId);
				ps.setString(4, tradeName);
				ps.setString(5, unitPrice);
				ps.setString(6, saleNumber);
				ps.setString(7, note);
				ps.setString(8, id);

				ps.executeUpdate();

				List<String> successes = new ArrayList<>();
				successes.add("No." + id + "の売上を更新しました。");
				session.setAttribute("successes", successes);

				session.setAttribute("data", null);
				session.setAttribute("preserve", null);

				resp.sendRedirect("S0021.html");

			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();

			} finally {
				try {
					DBUtils.close(con);
					DBUtils.close(ps);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}else if(req.getParameter("cancel")!= null) {

			DBUtils.getCategoriesAndAccounts(req, resp);

			String id = req.getParameter("id");

			CancelBeans data = new CancelBeans(
							req.getParameter("id"),
							req.getParameter("sale_date"),
							req.getParameter("name"),
							req.getParameter("account_id"),
							req.getParameter("category_id"),
							req.getParameter("trade_name"),
							req.getParameter("unit_price"),
							req.getParameter("sale_number"),
							req.getParameter("note")
						);

			session.setAttribute("data", data);

			session.setAttribute("preserve", null);

			resp.sendRedirect("S0023.html?id=" + id);

		}

	}
}
