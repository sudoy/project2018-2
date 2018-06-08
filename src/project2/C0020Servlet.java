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

@WebServlet("/C0020.html")
public class C0020Servlet extends HttpServlet {
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
			sql = "select sale_id, sale_date, (select category_name from categories c where s.category_id = c.category_id) as category_name," +
					"	trade_name, unit_price,  sale_number, note from sales s " +
					"	JOIN accounts a ON s.account_id = a.account_id ORDER BY s.sale_id;";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//SELECT命令を実行
			rs = ps.executeQuery();

			//ResultSetをJavaBeansに変換
			List<Sales> list = new ArrayList<>();

			while(rs.next()) {
				Sales sales = new Sales(rs.getInt("sale_id"),
							rs.getDate("sale_date"),
							rs.getString("category_name"),
							rs.getString("trade_name"),
							rs.getInt("unit_price"),
							rs.getInt("sale_number")
						);

				list.add(sales);
			}

			//JavaBeansをJSPへ渡す
			req.setAttribute("list", list);

			//foward→c0020.jsp
			getServletContext().getRequestDispatcher("/WEB-INF/c0020.jsp")
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
