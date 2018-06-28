package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import com.abc.asms.beans.InsertSales;
import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}

		//権限チェック
		if(!AuthorityUtils.checkSalesAuthority(req, resp)) {
			return;
		}

		//CategoriesテーブルとAccountsテーブルのデータをbeansに変換してjspに渡す
		DBUtils.getCategoriesAndAccounts(req, resp);

		req.setCharacterEncoding("UTF-8");

		LocalDateTime d = LocalDateTime.now();
		String today = DateTimeFormatter.ofPattern("yyyy/M/d").format(d);
		req.setAttribute("today", today);

		HttpSession session = req.getSession();

		InsertSales is = (InsertSales)session.getAttribute("is");

		if(is != null) {
			session.setAttribute("is", is);
			getServletContext().getRequestDispatcher("/WEB-INF/s0010.jsp")
				.forward(req, resp);
			return;
		}

		session.setAttribute("is", null);

		getServletContext().getRequestDispatcher("/WEB-INF/s0010.jsp")
			.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		String saleDate = req.getParameter("saleDate");
		String accountId = req.getParameter("accountId");
		String categoryId = req.getParameter("categoryId");
		String tradeName = req.getParameter("tradeName");
		String unitPrice = req.getParameter("unitPrice");
		String saleNumber = req.getParameter("saleNumber");
		String note = req.getParameter("note");

		DBUtils.getCategoriesAndAccounts(req, resp);

		InsertSales is = new InsertSales(saleDate,accountId,categoryId,tradeName,unitPrice,saleNumber,note);
		session.setAttribute("is", is);

		List<String> errors =  validate(saleDate, accountId, categoryId,
				tradeName, unitPrice, saleNumber, note);

		if(errors.size() > 0) {
			DBUtils.getCategoriesAndAccounts(req, resp);

			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/s0010.jsp")
				.forward(req, resp);
			return;
		}

		//アカウントテーブル存在確認チェック
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try{
			con = DBUtils.getConnection();

			//CategoriesテーブルとAccountsテーブルのデータをbeansに変換してjspに渡す
			DBUtils.getCategoriesAndAccounts(req, resp);

			sql = "SELECT account_id, name, mail, password, authority "
					+ "FROM accounts WHERE account_id = ?;";

			ps = con.prepareStatement(sql);

			ps.setString(1, accountId);

			rs = ps.executeQuery();

			//sqlが実行出来なかったらエラー　→　s0010に返す
			if(!rs.next()) {
				errors.add("アカウントテーブルに存在しません。");
				session.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/s0010.jsp")
					.forward(req, resp);
				return;
			}

			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
			}catch(Exception e){
				e.printStackTrace();
			}

			//商品カテゴリーテーブル存在確認チェック
			sql = "SELECT category_id, category_name, active_flg "
					+ "FROM categories WHERE category_id = ?;";

			ps = con.prepareStatement(sql);

			ps.setString(1, categoryId);

			rs = ps.executeQuery();

			//sqlが実行出来なかったらエラー　→　s0010に返す
			if(!rs.next()) {
				errors.add("商品カテゴリーテーブルに存在しません。");
				session.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/s0010.jsp")
					.forward(req, resp);
				return;
			}

			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
			}catch(Exception e){
				e.printStackTrace();
			}

		}catch(Exception e){
			e.printStackTrace();
			throw new ServletException(e);
		}finally{
			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		resp.sendRedirect("S0011.html");
	}

	private List<String> validate(String saleDate, String accountId, String categoryId,
			String tradeName, String unitPrice, String saleNumber, String note) {

		List<String> errors = new ArrayList<>();

		//販売日の必須入力
		if(saleDate.equals("")){
			errors.add("販売日を入力して下さい。");
		}

		//販売日の形式チェック
		if(!saleDate.equals("")) {
			try {
				if(LocalDate.parse(saleDate, DateTimeFormatter.ofPattern("uuuu/M/d")
						.withResolverStyle(ResolverStyle.STRICT)) != null ) {
					LocalDate.parse(saleDate, DateTimeFormatter.ofPattern("uuuu/M/d")
							.withResolverStyle(ResolverStyle.STRICT)) ;
				}else {
					LocalDate.parse(saleDate, DateTimeFormatter.ofPattern("uuuu/MM/dd")
							.withResolverStyle(ResolverStyle.STRICT));
				}
			}catch(Exception e) {
				errors.add("販売日を正しく入力して下さい。");
			}
		}

		//担当の必須入力
		if(accountId.equals("")) {
			errors.add("担当が未選択です。");
		}

		//商品カテゴリーの必須入力
		if(categoryId == null) {
			errors.add("商品カテゴリーが未選択です。");
		}

		//商品名の必須入力
		if(tradeName.equals("")) {
			errors.add("商品名を入力して下さい。");
		}

		//商品名の長さチェック
		if(tradeName.length() >= 101) {
			errors.add("商品名が長すぎます。");
		}

		//販売日の必須入力
		if(unitPrice.equals("")) {
			errors.add("単価を入力して下さい。");
		}

		//単価の長さチェック
		if(unitPrice.length() >= 10) {
			errors.add("単価が長すぎます。");
		}

		//単価整数かつ1以上にする
		if(!unitPrice.equals("")) {
		    try {
		        int i = Integer.parseInt(unitPrice);
				if(i <= 0) {
					throw new NumberFormatException();
				}
		    } catch (NumberFormatException e) {
		        errors.add("単価を正しく入力して下さい。");
		    }
		}

		//個数の必須入力
		if(saleNumber.equals("")) {
			errors.add("個数を入力して下さい。");
		}

		//個数の長さチェック
		if(saleNumber.length() >= 10) {
			errors.add("個数が長すぎます。");
		}

		//個数整数かつ1以上にする
		if(!saleNumber.equals("")) {
		    try {
		        int i = Integer.parseInt(saleNumber);
				if(i <= 0) {
					throw new NumberFormatException();
				}
		    } catch (NumberFormatException e) {
		        errors.add("個数を正しく入力して下さい。");
		    }
		}

		//備考の長さチェック
		if(note.length() >= 400) {
			errors.add("備考が長すぎます。");
		}

		return errors;

	}

}
