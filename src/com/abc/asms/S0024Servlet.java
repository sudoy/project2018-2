package com.abc.asms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/S0024.html")
public class S0024Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {


			req.getServletContext().getRequestDispatcher("/WEB-INF/s0024.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//仮実装
//		String sale_date = req.getParameter("sale_date");
		String name = req.getParameter("name");
		String category_name = req.getParameter("category_name");
		String trade_name = req.getParameter("trade_name");
		String unit_price = req.getParameter("unit_price");
		String sale_number = req.getParameter("sale_number");
		String sum = req.getParameter("sum");
		String note = req.getParameter("note");
		String sale_id = req.getParameter("sale_id");

		req.setAttribute("name", name);
		req.setAttribute("category_name", category_name);
		req.setAttribute("trade_name ", trade_name );
		req.setAttribute("unit_price", unit_price);
		req.setAttribute("sale_number", sale_number);
		req.setAttribute("sum", sum);
		req.setAttribute("note", note);
		req.setAttribute("sale_id", sale_id);





//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		try {
//			con = DBUtils.getConnection();
//
//			String sql = "select sale_id, sale_date, name,"
//						+" (select category_name from categories c where s.category_id = c.category_id) as category_name,"
//						+" trade_name, unit_price,sale_number, unit_price * sale_number as sum,"
//						+" note from sales s JOIN accounts a ON s.account_id = a.account_id"
//						+" where sale_id = ?";
//
//			ps = con.prepareStatement(sql);
//
//			ps.setString(1, id);
//
//			System.out.println(ps);
//
//			rs = ps.executeQuery();
//
//			if(!rs.next()) {
//				throw new Exception();
//			}

//			List<String> s24 = new ArrayList<>();
////					s24.add(sale_date);
//					s24.add(name);
//					s24.add(category_name);
//					s24.add(trade_name);
//					s24.add(unit_price);
//					s24.add(sale_number);
//					s24.add(sum);
//					s24.add(note);
//					s24.add(sale_id);

//			req.setAttribute("s24", s24);
		req.getServletContext().getRequestDispatcher("/WEB-INF/s0024.jsp").forward(req, resp);

	}

}
