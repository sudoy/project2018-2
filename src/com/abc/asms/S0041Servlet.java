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

import com.abc.asms.beans.S0021;
import com.abc.asms.utils.AuthorityUtils;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;

@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}
		//権限チェック
		if(!AuthorityUtils.tabDeleteAccountAuthority(req, resp)) {
			getServletContext().getRequestDispatcher("/WEB-INF/s0041a.jsp")
			.forward(req, resp);
		}
		req.setCharacterEncoding("UTF-8");
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String note = req.getParameter("note");
		String tradeName = req.getParameter("trade_name");
		String saleDate1 = req.getParameter("sale_date1");
		String saleDate2 = req.getParameter("sale_date2");
		String accountName = req.getParameter("name");
		String categoryName[] = req.getParameterValues("categoryName");
		try{
			con = DBUtils.getConnection();

			//SQL
			sql = "select * from accounts a left join sales s on a.account_id = s.account_id "
					+"left join categories c on c.category_id = s.category_id "
					+ "where 0 = 0 ";

			List<S0021> list1 = new ArrayList<>();
			//備考検索
			if(note != "") {
				sql += " and note like '%" + note + "%'";
			}else {
				sql += "";
			}

			//商品名検索
			if(tradeName != "") {
				sql += " and trade_name like '%" + tradeName + "%'";
			}else {
				sql += "";
			}

			//日付検索
			if(!saleDate1.equals("") && !saleDate2.equals("")) {
				sql += " and sale_date between '" + saleDate1 + "' and '" + saleDate2+ "'";

			}else if(saleDate1.equals("") && !saleDate2.equals("")){
				sql += " and sale_date <= '" + saleDate2 + "'";

			}else if(!saleDate1.equals("") && saleDate2.equals("")) {
				sql += " and sale_date >= '" + saleDate1 + "'";

			}else {
				sql += "";
			}

			//担当検索
			if(accountName !=  "") {
				sql += " and name =" + "'"+accountName+"'";
			}else {
				sql += "";
			}
			//カテゴリー検索
			if(req.getParameterValues("categoryName") == null) {
				sql += "";
			}
			if(categoryName != null) {
				sql += "and category_name in(";
				for(int i = 0; i < categoryName.length; i++) {
					if(i != categoryName.length-1) {
						sql += "'"+ categoryName[i] +"'"+",";
					}else {
						sql += "'"+ categoryName[i] +"'"+")";
					}
				}

			}

			//id降順
			sql += "order by sale_id";
			//SELECT命令の準備
			ps = con.prepareStatement(sql);
			//foward
			getServletContext().getRequestDispatcher("/WEB-INF/s0040.jsp")
				.forward(req, resp);
			//resp.sendRedirect("result.html");
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


		//JSPへフォワード
		getServletContext().getRequestDispatcher("/WEB-INF/s0041.jsp")
		.forward(req, resp);

	}

}
