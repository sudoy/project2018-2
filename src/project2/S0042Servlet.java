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

import project2.beans.Accounts;
import project2.utils.DBUtils;


@WebServlet("/S0042.html")
public class S0042Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();
			
			
		sql = "select * from accounts where account_id = ?";
		//select命令の準備
		ps = con.prepareStatement(sql);

		//select文にパラメータの内容をセット
		ps.setString(1, req.getParameter("id"));

		//select命令を実行
		rs = ps.executeQuery();
		
		rs.next();

		int id = rs.getInt("id");
		String name = rs.getString("name");
		String mail = rs.getString("mail");
		String password = rs.getString("password");
		int authority = rs.getInt("authority");

		 Accounts accounts = new Accounts(id, name,  mail,  password, authority);
		req.setAttribute("accounts", accounts);

		//JSPへフォワード
		getServletContext().getRequestDispatcher("/WEB-INF/detail.jsp")
				.forward(req, resp);

	} catch (Exception e) {
		throw new ServletException(e);
	} finally {
		try {
			if (rs != null) {
				con.close();
			}
			if (ps != null) {
				ps.close();
			}
			DBUtils.close(con);
		} catch (Exception e) {
		}
	}
}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher("/WEB-INF/s0042.jsp")
		.forward(req, resp);
	}
}
