package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.asms.beans.Sales;
import com.abc.asms.utils.DBUtils;

@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//foward→index.jsp
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
			//データベースの接続を確立
			con = DBUtils.getConnection();

			sql = "SELECT sale_id = ?, sale_date = ?, account_id = ?, category_id = ?, trade_name = ?, unit_price = ?, sale_number = ?, note = ?"
					+ " FROM sales;";

			ps = con.prepareStatement(sql);

			//SELECT文にパラメーターの内容をセット
			ps.setString(1, req.getParameter("saleId"));
			ps.setString(2, req.getParameter("saleDate"));
			ps.setString(3, req.getParameter("accountId"));
			ps.setString(4, req.getParameter("categoryId"));
			ps.setString(5, req.getParameter("tradeName"));
			ps.setString(6, req.getParameter("unitPrice"));
			ps.setString(7, req.getParameter("saleNumber"));
			ps.setString(8, req.getParameter("note"));

			//SELECT命令を実行
			rs = ps.executeQuery();

			//ResultSet→JavaBeansに変換する
			rs.next();

			Sales sales = new Sales(rs.getInt("sale_id"),
							rs.getDate("sale_date"),
							rs.getInt("account_id"),
							rs.getInt("category_id"),
							rs.getString("trade_name"),
							rs.getInt("unit_price"),
							rs.getInt("sale_number"),
							rs.getString("note")
							);

			req.setAttribute("sales", sales);

			//JSPへフォワード
			getServletContext().getRequestDispatcher("/WEB-INF/s0011.jsp")
				.forward(req, resp);

		}catch(Exception e){
			e.printStackTrace();
			throw new ServletException(e);

		}finally{
			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){}
		}

	}

}
