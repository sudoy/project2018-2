package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.asms.utils.DBUtils;

@WebServlet("/S0011.html")
public class S0011Servlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

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

		try {
			con = DBUtils.getConnection();

			sql = "INSERT INTO sales(sale_date, account_id, category_id, trade_name, unit_price, sale_number, note)"
					+ "values(?,?,?,?,?,?,?,?)";

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

			resp.sendRedirect("S0010.html");
		}catch(Exception e){
			throw new ServletException(e);

		}finally{
			try{
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){}
		}
	}
}
