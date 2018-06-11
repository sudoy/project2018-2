package project2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project2.beans.Sales;
import project2.utils.DBUtils;

@WebServlet("/S0011.html")
public class S0011Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();

			sql = "SELECT sale_date, account_id, category_id, trade_name, unit_price, sale_number, note"
					+ " FROM sales"
					+ " where sale_id = 9";

			ps = con.prepareStatement(sql);

			//SELECT文にパラメーターの内容をセット
//			ps.setString(1, req.getParameter("saleId"));

			System.out.println(ps);

			//SELCT命令を実行
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

			System.out.println(sales);

			req.setAttribute("sales", sales);



			//JSPへフォワード
			getServletContext().getRequestDispatcher("/WEB-INF/s0011.jsp")
				.forward(req, resp);

		}catch(Exception e){
			throw new ServletException(e);

		}finally{
			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String saleId = req.getParameter("saleId");
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

			sql = "INSERT INTO sales(sale_id, sale_date, account_id, category_id, trade_name, unit_price, sale_number, note)"
					+ "values(?,?,?,?,?,?,?,?)";

			//INSERT命令の準備
			ps = con.prepareStatement(sql);

			//INSERT命令にポストデータの内容をセット
			ps.setString(1, saleId);
			ps.setString(2, saleDate);
			ps.setString(3, accountId);
			ps.setString(4, categoryId);
			ps.setString(5, tradeName);
			ps.setString(6, unitPrice);
			ps.setString(7, saleNumber);
			ps.setString(8, note);

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
