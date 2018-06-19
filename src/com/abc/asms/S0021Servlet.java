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

import com.abc.asms.beans.S0021;
import com.abc.asms.beans.SearchKeepS;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.DBUtils2;
import com.abc.asms.utils.Utils;


@WebServlet("/S0021.html")
public class S0021Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// ログインチェック
		if(!Utils.checkLogin(req, resp)) {
			return;
		}
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		DBUtils2.getConnection2(req, resp);
		List<String> errors =  new ArrayList<>();
		if(errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/s0020.jsp")
			.forward(req, resp);
			return;
		}
		try{
			con = DBUtils.getConnection();
			SearchKeepS ss = (SearchKeepS)session.getAttribute("ss");

			//SQL
			sql = "select sale_id,sale_date,a.name,c.category_name, s.trade_name, s.unit_price, s.sale_number,note from accounts a left join sales s on a.account_id = s.account_id "
					+"left join categories c on c.category_id = s.category_id "
					+ "where 0 = 0 ";

			//備考検索
			if(ss.getNote() != "") {
				sql += " and note like '%" + ss.getNote() + "%'";
			}else {
				sql += "";
			}

			//商品名検索
			if(ss.getTradeName() != "") {
				sql += " and trade_name like '%" + ss.getTradeName() + "%'";
			}else {
				sql += "";
			}

			//日付検索
			if(!ss.getSaleDate1().equals("") && !ss.getSaleDate2().equals("")) {
				sql += " and sale_date between '" + ss.getSaleDate1() + "' and '" + ss.getSaleDate2()+ "'";

			}else if(ss.getSaleDate1().equals("") && !ss.getSaleDate2().equals("")){
				sql += " and sale_date <= '" + ss.getSaleDate2() + "'";

			}else if(!ss.getSaleDate1().equals("") && ss.getSaleDate2().equals("")) {
				sql += " and sale_date >= '" + ss.getSaleDate1() + "'";

			}else {
				sql += "";
			}
//
//			//担当検索
			if(ss.getAccountName() !=  "") {
				sql += " and name =" + "'"+ss.getAccountName()+"'";
			}else {
				sql += "";
			}
//			//カテゴリー検索
			if(req.getParameterValues("categoryName") == null) {
				sql += "";
			}
			if(ss.getCategoryName() != null) {
				sql += "and category_name in(";
				for(int i = 0; i < ss.getCategoryName().length; i++) {
					if(i != ss.getCategoryName().length-1) {
						sql += "'"+ ss.getCategoryName()[i] +"'"+",";
					}else {
						sql += "'"+ ss.getCategoryName()[i] +"'"+")";
					}
				}

			}

			//id降順
			sql += "order by sale_id";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);
			//SELECT命令を実行
			rs = ps.executeQuery();


			List<S0021> list = new ArrayList<>();
			while(rs.next()) {
				S0021 sale = new S0021(rs.getInt("sale_id"),
						rs.getDate("sale_date"),
						rs.getString("name"),
						rs.getString("category_name"),
						rs.getString("trade_name"),
						rs.getInt("unit_price"),
						rs.getInt("sale_number"),
						rs.getString("note"));

				list.add(sale);
			}
			if(list.isEmpty()) {
				errors = new ArrayList<>();
				errors.add("検索結果はありません。");
				session.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/s0020.jsp")
						.forward(req, resp);
				return;
			}
			//JavaBeansをJSPへ渡す

			req.setAttribute("list", list);

			//foward
			getServletContext().getRequestDispatcher("/WEB-INF/s0021.jsp")
				.forward(req, resp);
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




	}


}
