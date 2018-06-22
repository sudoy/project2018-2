package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0011.html")
public class S0011Servlet extends HttpServlet {

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

		req.getServletContext().getRequestDispatcher("/WEB-INF/s0010.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}

		//権限チェック
		if(!AuthorityUtils.checkSalesAuthority(req, resp)) {
			return;
		}

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		String saleDate = req.getParameter("saleDate");
		String accountId = req.getParameter("accountId");
		String categoryId = req.getParameter("categoryId");
		String tradeName = req.getParameter("tradeName");
		String unitPrice = req.getParameter("unitPrice");
		String saleNumber = req.getParameter("saleNumber");
		String note = req.getParameter("note");

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		int id = 0;

		try {
			con = DBUtils.getConnection();

			sql = "INSERT INTO sales(sale_date, account_id, category_id, trade_name, unit_price, sale_number, note)"
					+ "values(?,?,?,?,?,?,?);";

			//INSERT命令の準備
			ps = con.prepareStatement(sql);

			//INSERT命令にポストデータの内容をセット
			ps.setString(1, saleDate);
			ps.setString(2, accountId);
			ps.setString(3, categoryId);
			ps.setString(4, tradeName);
			ps.setString(5, unitPrice);
			ps.setString(6, saleNumber);
			ps.setString(7, note);

			ps.executeUpdate();

			try{
				DBUtils.close(ps);
			}catch(Exception e){
				e.printStackTrace();
			}

			//insertされた後のidをselect文で取り出す
			sql = "SELECT LAST_INSERT_ID() as id FROM sales";

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			if(rs.next()) {
				//sqlからidを取り出す
				id = rs.getInt("id");

				//成功メッセージ（遷移先で出る）
				List<String> successes = new ArrayList<>();
				successes.add("No" + id + "の売上を登録しました。");
				session.setAttribute("successes", successes);
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

		//登録処理後、登録画面に遷移
		resp.sendRedirect("S0010.html");
	}
}
