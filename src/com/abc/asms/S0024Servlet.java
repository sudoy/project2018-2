package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.beans.Accounts;
import com.abc.asms.beans.Categories;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.HtmlUtils;


@WebServlet("/S0024.html")
public class S0024Servlet extends HttpServlet {
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {


//		try {
//			con = DBUtils.getConnection();
//
//			sql = "select sale_id, sale_date, name,"
//					+" (select category_name from categories c where s.category_id = c.category_id) as category_name,"
//					+" trade_name, unit_price,sale_number, unit_price * sale_number as sum,"
//					+" note from sales s JOIN accounts a ON s.account_id = a.account_id"
//					+" where sale_id = ?";
//
//			ps = con.prepareStatement(sql);
//			ps.setString(1, id);
//
//			rs = ps.executeQuery();
//
//			if(!rs.next()) {
//				throw new Exception();
//			}
//
//			Detail_beans s24 = new Detail_beans(
//					Utils.date2LocalDate(rs.getDate("sale_date")),
//					rs.getString("name"),
//					rs.getString("category_name"),
//					rs.getString("trade_name"),
//					rs.getInt("unit_price"),
//					rs.getInt("sale_number"),
//					rs.getInt("sum"),
//					rs.getString("note"),
//					rs.getInt("sale_id")
//
//				);
//
//			req.setAttribute("s24", s24);
//
//			req.getServletContext().getRequestDispatcher("/WEB-INF/s0024.jsp")
//			.forward(req, resp);
//
//		}catch(Exception e) {
//			throw new ServletException(e);
//		}finally {
//			try {
//				DBUtils.close(con);
//				DBUtils.close(ps);
//				DBUtils.close(rs);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {


		//ログインチェック
		if (!HtmlUtils.checkLogin(req, resp)) {
			return;
		}

		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();

		if(req.getParameter("update") != null ) {

		//	HttpSession session = req.getSession();

			String id = req.getParameter("id");

			String sale_date = req.getParameter("sale_date");
			String account_id = req.getParameter("account_id");
			String category_id = req.getParameter("category_id");
			String trade_name = req.getParameter("trade_name");
			String unit_price = req.getParameter("unit_price");
			String sale_number = req.getParameter("sale_number");
			String sum = req.getParameter("sum");
			String note = req.getParameter("note");


//			List<String> errors = validate(sale_date);
//			System.out.println(errors);
//
//			if(errors.size() > 0) {
//
//				session.setAttribute("errors", errors);
//
//				getServletContext().getRequestDispatcher("/WEB-INF/update.jsp")
//					.forward(req, resp);
//
//				return;
//			}

			Connection con = null;
			PreparedStatement ps = null;

			try {
				con = DBUtils.getConnection();
				//仮実装 ラジオボタンに対応していない。
				String sql = ""
							+ "update sales set "
							+ "sale_date = ?, account_id = ?, category_id = 1, trade_name = ?, "
							+ "unit_price = ?, sale_number = ?, note = ? "
							+ "where sale_id = ?";

				ps = con.prepareStatement(sql);

				ps.setString(1, sale_date);
				ps.setString(2, account_id);
//				ps.setString(3, category_id);
				ps.setString(3, trade_name);
				ps.setString(4, unit_price);
				ps.setString(5, sale_number);
				ps.setString(6, note);
				ps.setString(7, id);

				ps.executeUpdate();

				List<String> successes = new ArrayList<>();
				successes.add("No." + id + "の売上を更新しました。");
				session.setAttribute("successes", successes);

				System.out.println(successes);//確認用

				resp.sendRedirect("S0022.html?id="+ id);


			} catch (Exception e) {
				throw new ServletException(e);

			}finally {
					try {
						DBUtils.close(con);
						DBUtils.close(ps);
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}





			//わかりやすく間開けてるだけ（後日削除）

		}else if(req.getParameter("check") != null) {

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql;

			String id = req.getParameter("id");

			String sale_date = req.getParameter("sale_date");
			String name = req.getParameter("name");
			String accountId = req.getParameter("accountId");
			String trade_name = req.getParameter("trade_name");
			String unit_price = req.getParameter("unit_price");
			String sale_number = req.getParameter("sale_number");
			String note = req.getParameter("note");

			List<String> errors = validate(sale_date);
			System.out.println(errors);

			if(errors.size() > 0) {

				session.setAttribute("errors", errors);

				req.getServletContext().getRequestDispatcher("/WEB-INF/s0023.html").forward(req, resp);

				return;
			}


			try{
				con = DBUtils.getConnection();

				//SQL
				sql = "select category_id,category_name, active_flg from categories";
				//SELECT命令の準備
				ps = con.prepareStatement(sql);

				//SELECT命令を実行
				rs = ps.executeQuery();

				List<Categories> categories = new ArrayList<>();
				while(rs.next()) {
					Categories category = new Categories(
							rs.getInt("category_id"),
							rs.getString("category_name"),
							rs.getInt("active_flg"));
					categories.add(category);
				}

				req.setAttribute("categories", categories);

			} catch (Exception e) {
				throw new ServletException(e);

			}finally {
					try {
						DBUtils.close(con);
						DBUtils.close(ps);
						DBUtils.close(rs);
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}

			try{

			con = DBUtils.getConnection();

			sql = "select account_id,name,mail,password,authority  from accounts";
			//SELECT命令の準備
			ps = con.prepareStatement(sql);
			//SELECT命令を実行
			rs = ps.executeQuery();
			List<Accounts> accounts = new ArrayList<>();
			while(rs.next()) {
				Accounts account = new Accounts(
						rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getInt("authority"));

				accounts.add(account);
			}
				req.setAttribute("accounts", accounts);

			} catch (Exception e) {
				throw new ServletException(e);

			}finally {
					try {
						DBUtils.close(con);
						DBUtils.close(ps);
						DBUtils.close(rs);
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			req.getServletContext().getRequestDispatcher("/WEB-INF/s0024.jsp").forward(req, resp);
		}


//			private List<String> validate(String sale_date) {
//				if(!sale_date.equals("")) {
//					try {
//						if(LocalDate.parse(sale_date, DateTimeFormatter.ofPattern("uuuu/M/d")
//								.withResolverStyle(ResolverStyle.STRICT)) != null ) {
//							LocalDate.parse(sale_date, DateTimeFormatter.ofPattern("uuuu/M/d")
//									.withResolverStyle(ResolverStyle.STRICT)) ;
//						}
//					}catch(Exception e) {
//						errors.add("販売日（検索開始日）を正しく入力して下さい。");
//					}
//				}
//				return null;
//			}
		}

	private List<String> validate(String sale_date) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
