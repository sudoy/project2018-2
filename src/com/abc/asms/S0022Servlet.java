package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.asms.beans.S0022;
import com.abc.asms.utils.DBUtils;
import com.abc.asms.utils.Utils;


@WebServlet("/S0022.html")
public class S0022Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//ログインチェック
		if (!Utils.checkLogin(req, resp)) {
			return;
		}

		req.setCharacterEncoding("utf-8");

		//詳細で指定された売上IDを取得
		String id = req.getParameter("id");

		//DB接続開始
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//売上IDからすべてのデータをセレクト出力
		try {
			con = DBUtils.getConnection();
			String sql = "select sale_id, sale_date,name,"
						+" (select category_name from categories c where s.category_id = c.category_id)"
						+" as category_name, trade_name,unit_price, sale_number,"
						+" note from sales s"
						+" JOIN accounts a ON s.account_id = a.account_id"
						+" where sale_id = ?";

			//上記SQL実施
			ps = con.prepareStatement(sql);

			//プレースホルダにIDセット
			ps.setString(1, id);

			//実行
			rs = ps.executeQuery();


			if(!rs.next()) {
				throw new ServletException();
			}

			//s22へ取得したデータをセット
			S0022 s22 = new S0022(
						rs.getInt("sale_id"),
						rs.getDate("sale_date"),
						rs.getString("name"),
						rs.getString("category_name"),
						rs.getString("trade_name"),
						rs.getInt("unit_price"),
						rs.getInt("sale_number"),
						rs.getString("note")
					);

			//設定
			req.setAttribute("s22", s22);

			req.getServletContext().getRequestDispatcher("/WEB-INF/s0022.jsp").forward(req, resp);


		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}finally {
			try {
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
