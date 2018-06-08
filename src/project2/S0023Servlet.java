package project2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project2.utils.DBUtils;

@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {


		//仮実装
		String saleId = req.getParameter("sale_id");

		Connection con = null;
		PreparedStatement ps = null;



		try {
			con = DBUtils.getConnection();

			String sql = "select sale_date, name,"
						+" (select category_name from categories c where s.category_id = c.category_id) as category_name,"
						+" trade_name, unit_price,  sale_number, unit_price * sale_number as sum,"
						+" note from sales s JOIN accounts a ON s.account_id = a.account_id"
						+" where sale_id = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, saleId);

			ps.executeQuery();




		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				DBUtils.close(ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}





		req.getServletContext().getRequestDispatcher("/WEB-INF/s0023.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getServletContext().getRequestDispatcher("/WEB-INF/s0023.jsp").forward(req, resp);

	}
}
