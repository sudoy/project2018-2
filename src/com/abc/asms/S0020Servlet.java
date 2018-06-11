package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project2.beans.Accounts;
import project2.beans.Categories;
import project2.utils.DBUtils;
@WebServlet("/S0020.html")
public class S0020Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		LocalDateTime d = LocalDateTime.now();
		String today = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(d);
		session.setAttribute("today", today);


		try{
			con = DBUtils.getConnection();

			//SQL
			sql = "select category_id,category_name, active_flg from categories";
			//SELECT命令の準備
			ps = con.prepareStatement(sql);
			//SELECT命令を実行
			rs = ps.executeQuery();
			List<Categories> list = new ArrayList<>();
			while(rs.next()) {
				Categories category = new Categories(rs.getInt("category_id"),
						rs.getString("category_name"),
						rs.getInt("active_flg"));
				list.add(category);
			}
			//JavaBeansをJSPへ渡す
			req.setAttribute("list", list);

			sql = "select account_id,name,mail,password,authority  from accounts";
			//SELECT命令の準備
			ps = con.prepareStatement(sql);
			//SELECT命令を実行
			rs = ps.executeQuery();
			List<Accounts> list2 = new ArrayList<>();
			while(rs.next()) {
				Accounts account = new Accounts(rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getInt("authority"));

				list2.add(account);
			}
			//JavaBeansをJSPへ渡す
			req.setAttribute("list2", list2);

			//foward
			getServletContext().getRequestDispatcher("/WEB-INF/s0020.jsp")
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

}
