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
import com.abc.asms.utils.DBUtils;


@WebServlet("/S0021.html")
public class S0021Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try{
			con = DBUtils.getConnection();

			//SQL
			sql = "select s.sale_id, s.sale_date, a.name, c.category_name, s.trade_name,s.unit_price, s.sale_number, s.unit_price * s.sale_number as total, "
					+ "a.account_id, a.name,c.category_id, note " +
					"from accounts a left join sales s on a.account_id = s.account_id left join categories c on c.category_id = s.category_id";

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
			//JavaBeansをJSPへ渡す
			req.setAttribute("list", list);


			//foward
			getServletContext().getRequestDispatcher("/WEB-INF/s0021.jsp")
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


	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String note = req.getParameter("note");
		String tradeName = req.getParameter("trade_name");
		String saleDate1 = req.getParameter("sale_date1");
		String saleDate2 = req.getParameter("sale_date2");
		String accountName = req.getParameter("${project2.name}");

//		List<String> errors =  validate(saleDate1,saleDate2);
//		if(errors.size() > 0) {
//			getServletContext().getRequestDispatcher("/WEB-INF/s0020.jsp")
//				.forward(req, resp);
//			return;
//		}
		try{
			con = DBUtils.getConnection();

			//SQL
			sql = "select * from accounts a left join sales s on a.account_id = s.account_id "
					+"left join categories c on c.category_id = s.category_id "
					+ "where note like ? or trade_name like ? or sale_date between ? and ? or a.name = ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);
			//備考検索
			ps.setString(1, req.getParameter("note").equals("") ? null : "%" + note + "%");
			//商品名検索
			ps.setString(2, req.getParameter("trade_name").equals("") ? null : "%" + tradeName + "%");
			//日付検索
			if(!saleDate1.equals("") && !saleDate2.equals("")) {
				ps.setString(3, saleDate1);
				ps.setString(4, saleDate2);
			}else if(saleDate1.equals("") && !saleDate2.equals("")){
				ps.setString(3, "1900-01-01");
				ps.setString(4, saleDate2);
			}else if(!saleDate1.equals("") && saleDate2.equals("")) {
				ps.setString(3, saleDate1);
				ps.setString(4, "2099-01-01");
			}else {
				ps.setString(3, "");
				ps.setString(4, "");
			}

			//担当検索
			ps.setString(5, accountName);
			System.out.println(ps);
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
//	private List<String> validate(String saleDate1,String saleDate2) {
//		List<String> errors = new ArrayList<>();
//
//		//日付の必須入力
//		if(saleDate1.equals("") && saleDate2.equals("")){
//			errors.add("日付は必須入力です。");
//		}
//		if(!saleDate1.equals("")) {
//			try {
//				LocalDate.parse(saleDate1, DateTimeFormatter.ofPattern("uuuu/MM/dd")
//					.withResolverStyle(ResolverStyle.STRICT));
//			}catch(Exception e) {
//				errors.add("期限は「YYYY/MM/DD」形式で入力して下さい。");
//			}
//		}
//
//		if(!saleDate2.equals("")) {
//			try {
//				LocalDate.parse(saleDate2, DateTimeFormatter.ofPattern("uuuu/MM/dd")
//					.withResolverStyle(ResolverStyle.STRICT));
//			}catch(Exception e) {
//				errors.add("期限は「YYYY/MM/DD」形式で入力して下さい。");
//			}
//		}
//		return errors;
//
//
//
//	}


}
