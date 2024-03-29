package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.servlet.http.HttpSession;

import com.abc.asms.beans.CancelBeans;
import com.abc.asms.beans.Sales;
import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		HttpSession session = req.getSession();

		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}

		//権限チェック
		if (!AuthorityUtils.checkSalesAuthority(req, resp)) {
			return;
		}

		session.setAttribute("preserve", null);

		CancelBeans data = (CancelBeans) session.getAttribute("data");

		session.setAttribute("data", data);

		if (req.getParameter("id") == null) {
			List<String> errors = new ArrayList<>();
			errors.add("不正なアクセスです。");
			session.setAttribute("errors", errors);
			resp.sendRedirect("S0020.html");
			return;
		}

		//アカウントとカテゴリーの情報取得
		DBUtils.getCategoriesAndAccounts(req, resp);

		getServletContext().getRequestDispatcher("/WEB-INF/s0023.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		if (req.getParameter("check") != null) {

			//ログインチェック
			if (!Utils.checkLogin(req, resp)) {
				return;
			}

			//権限チェック
			if (!AuthorityUtils.checkSalesAuthority(req, resp)) {
				return;
			}

			req.setCharacterEncoding("utf-8");

			HttpSession session = req.getSession();

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql;

			String saleDate = req.getParameter("sale_date");
			String accountId = req.getParameter("account_id");
			String categoryId = req.getParameter("category_id");
			String tradeName = req.getParameter("trade_name");
			String unitPrice = req.getParameter("unit_price");
			String saleNumber = req.getParameter("sale_number");
			String note = req.getParameter("note");

			//エラー処理セット
			List<String> errors = validate(saleDate, accountId, categoryId, tradeName, unitPrice, saleNumber,
					note);

			if (errors.size() > 0) {
				DBUtils.getCategoriesAndAccounts(req, resp);

				session.setAttribute("errors", errors);

				session.setAttribute("data", null);

				getServletContext().getRequestDispatcher("/WEB-INF/s0023.jsp")
						.forward(req, resp);
				return;
			}

			//アカウントとカテゴリーの情報取得
			DBUtils.getCategoriesAndAccounts(req, resp);

			try {
				con = DBUtils.getConnection();

				sql = "select account_id,name,mail,password,authority from accounts where account_id = ?";
				ps = con.prepareStatement(sql);

				ps.setString(1, accountId);

				rs = ps.executeQuery();

				//sqlが実行出来なかったらエラー
				if (!rs.next()) {
					errors.add("アカウントテーブルに存在しません。");
					session.setAttribute("errors", errors);

					session.setAttribute("data", null);
					getServletContext().getRequestDispatcher("/WEB-INF/s0023.jsp")
							.forward(req, resp);
					return;
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			} finally {
				try {
					DBUtils.close(rs);
					DBUtils.close(ps);
					DBUtils.close(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//商品テーブル存在確認チェック
			try {
				con = DBUtils.getConnection();

				sql = "select category_id,category_name,active_flg from categories where category_id = ?";

				ps = con.prepareStatement(sql);

				ps.setString(1, categoryId);

				rs = ps.executeQuery();

				if (!rs.next()) {
					errors.add("商品カテゴリーテーブルに存在しません。");
					session.setAttribute("errors", errors);

					session.setAttribute("data", null);
					getServletContext().getRequestDispatcher("/WEB-INF/s0023.jsp")
							.forward(req, resp);
					return;
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			} finally {
				try {
					DBUtils.close(rs);
					DBUtils.close(ps);
					DBUtils.close(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			DBUtils.getCategoriesAndAccounts(req, resp);

			Sales preserve = new Sales(
							Integer.parseInt(req.getParameter("id")),
							Date.valueOf(req.getParameter("sale_date").replaceAll("/", "-")),
							Integer.parseInt(req.getParameter("account_id")),
							Integer.parseInt(req.getParameter("category_id")),
							req.getParameter("trade_name"),
							Integer.parseInt(req.getParameter("unit_price")),
							Integer.parseInt(req.getParameter("sale_number")),
							req.getParameter("note")
					);

			session.setAttribute("preserve", preserve);

			resp.sendRedirect("S0024.html");

		}else{

			//ログインチェック
			if (!Utils.checkLogin(req, resp)) {
				return;
			}

			//権限チェック
			if (!AuthorityUtils.checkSalesAuthority(req, resp)) {
				return;
			}

			HttpSession session = req.getSession();

			req.setCharacterEncoding("utf-8");

			String id = req.getParameter("id");

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql;

			DBUtils.getCategoriesAndAccounts(req, resp);

			try {
				con = DBUtils.getConnection();

				sql = "select sale_id, sale_date, name,"
						+ " category_id, a.account_id,"
						+ " trade_name, unit_price,sale_number, unit_price * sale_number as sum,"
						+ " note from sales s LEFT JOIN accounts a ON s.account_id = a.account_id"
						+ " where sale_id = ?";

				ps = con.prepareStatement(sql);

				ps.setString(1, id);

				rs = ps.executeQuery();

				if (!rs.next()) {
					throw new Exception();
				}

				Sales preserve = new Sales(
						rs.getInt("sale_id"),
						rs.getDate("sale_date"),
						rs.getInt("account_id"),
						rs.getInt("category_id"),
						rs.getString("trade_name"),
						rs.getInt("unit_price"),
						rs.getInt("sale_number"),
						rs.getString("note"));

				session.setAttribute("preserve", preserve);

				req.getServletContext().getRequestDispatcher("/WEB-INF/s0023.jsp").forward(req, resp);

			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			} finally {
				try {
					DBUtils.close(con);
					DBUtils.close(ps);
					DBUtils.close(rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}

	private List<String> validate(String saleDate, String accountId, String categoryId, String tradeName,
			String unitPrice, String saleNumber, String note) {
		List<String> errors = new ArrayList<>();

		//1-1 販売日必須入力チェック
		if (saleDate.equals("")) {
			errors.add("販売日を入力して下さい。");
		}

		//1-2 販売日形式チェック

		if (!saleDate.equals("")) {
			try {
				if (LocalDate.parse(saleDate, DateTimeFormatter.ofPattern("uuuu/M/d")
						.withResolverStyle(ResolverStyle.STRICT)) != null) {
					LocalDate.parse(saleDate, DateTimeFormatter.ofPattern("uuuu/M/d")
							.withResolverStyle(ResolverStyle.STRICT));
				} else {
					LocalDate.parse(saleDate, DateTimeFormatter.ofPattern("uuuu/MM/dd")
							.withResolverStyle(ResolverStyle.STRICT));
				}

			} catch (Exception e) {
				errors.add("販売日を正しく入力して下さい。");
			}
		}

		//1-3 担当者必須入力チェック
		if (accountId.equals("") || accountId.equals("0")) {
			errors.add("担当が未選択です。");
		}

		//1-4 商品カテゴリー必須入力チェック
		if (categoryId == null) {
			errors.add("商品カテゴリーが未選択です。");
		}

		//1-5 商品名必須入力チェック
		if (tradeName.equals("")) {
			errors.add("商品名を入力して下さい。");
		}

		//1-6 商品名長さチェック
		if (tradeName.length() >= 101) {
			errors.add("商品名が長すぎます。");
		}
		//1-7 単価必須入力チェック
		if (unitPrice.equals("")) {
			errors.add("単価を入力して下さい。");
		}
		//1-8 単価形式チェック
		if (!unitPrice.equals("")) {
			try {
				int i = Integer.parseInt(unitPrice);
				if (i <= 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				errors.add("単価を正しく入力して下さい。");
			}
		}

		//1-9 単価長さチェック
		if (unitPrice.length() >= 10) {
			errors.add("単価が長すぎます。");
		}
		//1-10 個数必須入力チェック
		if (saleNumber.equals("")) {
			errors.add("個数を入力して下さい。");
		}
		//1-11 個数形式チェック
		if (!saleNumber.equals("")) {
			try {
				int i = Integer.parseInt(saleNumber);
				if (i <= 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				errors.add("個数を正しく入力して下さい。");
			}
		}

		//1-12 個数長さチェック
		if (saleNumber.length() >= 10) {
			errors.add("個数が長すぎます。");
		}

		//1-13 備考長さチェック
		if (note.length() >= 401) {
			errors.add("備考が長すぎます。");
		}

		return errors;

	}
}
