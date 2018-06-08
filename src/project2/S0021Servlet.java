package project2;

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

import project2.beans.Sales;
import project2.utils.DBUtils;


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
			sql = "select s.sale_id, s.sale_date, a.name, c.category_name, "
					+ "s.trade_name,s.unit_price, s.sale_number, s.unit_price * s.sale_number as total "
					+"from accounts a left join sales s on a.account_id = s.account_id "
					+"left join categories c on c.category_id = s.category_id";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);


			//SELECT命令を実行
			rs = ps.executeQuery();
			System.out.println(rs);



			List<Sales> list = new ArrayList<>();

			while(rs.next()) {
				Sales sale = new Sales(rs.getInt("saleId"),
						rs.getDate("saleDate"),
						rs.getInt("accountId"),
						rs.getInt("categoryId"),
						rs.getString("tradeName"),
						rs.getInt("unitPrice"),
						rs.getInt("saleNumber"),
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
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		Connection con = null;
//		PreparedStatement ps = null;
//		String sql = null;
//		ResultSet rs = null;
//
//		String note = req.getParameter("note");
//		try{
//			con = DBUtils.getConnection();
//
//			//SQL
//			sql = "select s.sale_id, s.sale_date, a.name, c.category_name, "
//					+ "s.trade_name,s.unit_price, s.sale_number, s.unit_price * s.sale_number as total"
//					+"from accounts a left join sales s on a.account_id = s.account_id"
//					+"left join categories c on c.category_id = s.category_id";
//
//			//SELECT命令の準備
//			ps = con.prepareStatement(sql);
//
//
//
//			//SELECT命令を実行
//			rs = ps.executeQuery();
//
//
//			List<Sales> list = new ArrayList<>();
//
//			while(rs.next()) {
//				Sales sale = new Sales(rs.getInt("saleId"),
//						rs.getDate("saleDate"),
//						rs.getInt("accountId"),
//						rs.getInt("categoryId"),
//						rs.getString("tradeName"),
//						rs.getInt("unitPrice"),
//						rs.getInt("saleNumber"),
//						rs.getString("note"));
//
//				list.add(sale);
//			}
//
//			//JavaBeansをJSPへ渡す
//			req.setAttribute("list", list);
//			//foward
//			getServletContext().getRequestDispatcher("/WEB-INF/s0021.jsp")
//				.forward(req, resp);
//			//resp.sendRedirect("result.html");
//		}catch(Exception e){
//			throw new ServletException(e);
//		}finally{
//			//終了処理
//			try{
//				DBUtils.close(rs);
//				DBUtils.close(ps);
//				DBUtils.close(con);
//			}catch(Exception e){
//			}
//		}
//	}


}
