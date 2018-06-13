package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import com.abc.asms.beans.Accounts;
import com.abc.asms.beans.Categories;
import com.abc.asms.utils.DBUtils;

@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try{
			con = DBUtils.getConnection();

			//SQL
			sql = "select category_id,category_name, active_flg from categories where active_flg = 1";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//SELECT命令を実行
			rs = ps.executeQuery();

			List<Categories> list = new ArrayList<>();
			while(rs.next()) {
				Categories category = new Categories(rs.getInt("category_id"),
						rs.getString("category_name"),
						rs.getInt("active_flg"));
				list.add(category);
			}

			//JavaBeansをJSPへ渡す
			req.setAttribute("list", list);

			try{
				DBUtils.close(ps);
				DBUtils.close(rs);
			}catch(Exception e){}

			sql = "select account_id,name,mail,password,authority  from accounts";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//SELECT命令を実行
			rs = ps.executeQuery();

			List<Accounts> list2 = new ArrayList<>();
			while(rs.next()) {
				Accounts account = new Accounts(rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getInt("authority"));

				list2.add(account);
			}

			//JavaBeansをJSPへ渡す
			req.setAttribute("list2", list2);

		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			//終了処理
			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){
			}
		}

		getServletContext().getRequestDispatcher("/WEB-INF/s0010.jsp")
			.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try{
			con = DBUtils.getConnection();

			//SQL
			sql = "select category_id,category_name, active_flg from categories where active_flg = 1";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//SELECT命令を実行
			rs = ps.executeQuery();

			List<Categories> list = new ArrayList<>();
			while(rs.next()) {
				Categories category = new Categories(rs.getInt("category_id"),
						rs.getString("category_name"),
						rs.getInt("active_flg"));
				list.add(category);
			}

			//JavaBeansをJSPへ渡す
			req.setAttribute("list", list);

			try{
				DBUtils.close(ps);
				DBUtils.close(rs);
			}catch(Exception e){}

			sql = "select account_id,name,mail,password,authority  from accounts";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//SELECT命令を実行
			rs = ps.executeQuery();

			List<Accounts> list2 = new ArrayList<>();
			while(rs.next()) {
				Accounts account = new Accounts(rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getInt("authority"));

				list2.add(account);
			}

			//JavaBeansをJSPへ渡す
			req.setAttribute("list2", list2);

			try{
				DBUtils.close(ps);
				DBUtils.close(rs);
			}catch(Exception e){}


		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			//終了処理
			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){
			}
		}

		String saleDate = req.getParameter("saleDate");
		String accountId = req.getParameter("accountId");
		String categoryId = req.getParameter("categoryId");
		String tradeName = req.getParameter("tradeName");
		String unitPrice = req.getParameter("unitPrice");
		String saleNumber = req.getParameter("saleNumber");
		String note = req.getParameter("note");
		String name = req.getParameter("name");
		String categoryName = req.getParameter("categoryName");

		req.setAttribute("saleDate", saleDate);
		req.setAttribute("accountId", accountId);
		req.setAttribute("categoryId", categoryId);
		req.setAttribute("tradeName", tradeName);
		req.setAttribute("unitPrice", unitPrice);
		req.setAttribute("saleNumber", saleNumber);
		req.setAttribute("note", note);
		req.setAttribute("name", name);
		req.setAttribute("categoryName", categoryName);

		List<String> errors =  validate(saleDate, name, categoryName, tradeName, unitPrice, saleNumber, note);
		if(errors.size() > 0) {
			req.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/s0010.jsp")
				.forward(req, resp);
			return;
		}

		getServletContext().getRequestDispatcher("/WEB-INF/s0011.jsp")
			.forward(req, resp);
	}

	private List<String> validate(String saleDate, String name, String categoryName, String tradeName, String unitPrice, String saleNumber, String note) {
		List<String> errors = new ArrayList<>();

		//販売日の必須入力
		if(saleDate.equals("")){
			errors.add("販売日を入力して下さい。");
		}

		//販売日の形式（yyyy/MM//dd）
		if(!saleDate.equals("")) {
			try {
				LocalDate.parse(saleDate, DateTimeFormatter.ofPattern("uuuu/MM/dd")
					.withResolverStyle(ResolverStyle.STRICT));
			}catch(Exception e) {
				errors.add("販売日を正しく入力して下さい。");
			}
		}

		//担当の必須入力
		if(name.equals("")) {
			errors.add("担当が未選択です。");
		}

		//商品カテゴリーの必須入力
		if(categoryName.equals("")) {
			errors.add("商品カテゴリーが未選択です。");
		}

		//商品名の必須入力
		if(tradeName.equals("")) {
			errors.add("商品名を入力して下さい。");
		}

		//商品名の長さチェック
		if(categoryName.length() >= 101) {
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
				if(i < 0) {
					throw new NumberFormatException();
				}
		    } catch (NumberFormatException e) {
		        errors.add("単価を正しく入力してください。");
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
		        int i = Integer.parseInt(unitPrice);
				if(i < 0) {
					throw new NumberFormatException();
				}
		    } catch (NumberFormatException e) {
		        errors.add("個数を正しく入力してください。");
		    }
		}

		//備考の長さチェック
		if(note.length() >= 400) {
			errors.add("備考が長すぎます。");
		}

		return errors;

	}

}
