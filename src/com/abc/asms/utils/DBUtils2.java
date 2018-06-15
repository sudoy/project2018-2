package com.abc.asms.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.asms.beans.Accounts;
import com.abc.asms.beans.Categories;

public class DBUtils2 {
	public static void getConnection2(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {

		//担当と商品カテゴリー出すためのメソッド
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try{
			con = DBUtils.getConnection();

			//SQL
			sql = "select category_id,category_name, active_flg from categories where active_flg = 1";

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

			try{
				DBUtils.close(ps);
				DBUtils.close(rs);
			}catch(Exception e){}

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
