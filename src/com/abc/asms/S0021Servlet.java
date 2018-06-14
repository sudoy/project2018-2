package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.beans.Accounts;
import com.abc.asms.beans.Categories;
import com.abc.asms.beans.S0021;
import com.abc.asms.utils.DBUtils;


@WebServlet("/S0021.html")
public class S0021Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// ログインチェック
//		if(!HtmlUtils.checkLogin(req, resp)) {
//			return;
//		}

		//foward
		getServletContext().getRequestDispatcher("/WEB-INF/s0021.jsp")
			.forward(req, resp);


	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// ログインチェック
//		if(!HtmlUtils.checkLogin(req, resp)) {
//			return;
//		}
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String note = req.getParameter("note");
		String tradeName = req.getParameter("trade_name");
		String saleDate1 = req.getParameter("sale_date1");
		String saleDate2 = req.getParameter("sale_date2");
		String accountName = req.getParameter("name");
		String categoryName[] = req.getParameterValues("categoryName");
		try {
		//SQL
		con = DBUtils.getConnection();
		sql = "select category_id,category_name, active_flg from categories";
		//SELECT命令の準備
		ps = con.prepareStatement(sql);
		//SELECT命令を実行
		rs = ps.executeQuery();
		List<Categories> list1 = new ArrayList<>();
		while(rs.next()) {
			Categories category = new Categories(rs.getInt("category_id"),
					rs.getString("category_name"),
					rs.getInt("active_flg"));
			list1.add(category);
		}
		//JavaBeansをJSPへ渡す
		req.setAttribute("list1", list1);
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

		List<String> errors =  validate(saleDate1,saleDate2,req,accountName);
		if(errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/s0020.jsp")
			.forward(req, resp);
			return;
		}
		try{
			con = DBUtils.getConnection();

			//SQL
			sql = "select * from accounts a left join sales s on a.account_id = s.account_id "
					+"left join categories c on c.category_id = s.category_id "
					+ "where 0 = 0 ";

			//備考検索
			if(note != "") {
				sql += " and note like '%" + note + "%'";
			}else {
				sql += "";
			}

			//商品名検索
			if(tradeName != "") {
				sql += " and trade_name like '%" + tradeName + "%'";
			}else {
				sql += "";
			}

			//日付検索
			if(!saleDate1.equals("") && !saleDate2.equals("")) {
				sql += " and sale_date between '" + saleDate1 + "' and '" + saleDate2+ "'";

			}else if(saleDate1.equals("") && !saleDate2.equals("")){
				sql += " and sale_date <= '" + saleDate2 + "'";

			}else if(!saleDate1.equals("") && saleDate2.equals("")) {
				sql += " and sale_date >= '" + saleDate1 + "'";

			}else {
				sql += "";
			}

			//担当検索
			if(accountName !=  "") {
				sql += " and name =" + "'"+accountName+"'";
			}else {
				sql += "";
			}
			//カテゴリー検索
			sql += "and category_name in(";
			for(int i = 0; i < categoryName.length; i++) {
				if(i != categoryName.length-1) {
					sql += "'"+ categoryName[i] +"'"+",";
				}else {
					sql += "'"+ categoryName[i] +"'"+")";
				}
			}

			//SELECT命令の準備
			ps = con.prepareStatement(sql);
			System.out.println(ps);
			//SELECT命令を実行
			rs = ps.executeQuery();


			List<S0021> list = new ArrayList<>();
			while(rs.next()) {
				S0021 sale = new S0021(rs.getInt("sale_id"),
						rs.getDate("sale_date"),
						rs.getString("name"),
						rs.getString("category_name"),
						rs.getString("trade_name"),
						rs.getInt("unit_price"),
						rs.getInt("sale_number"),
						rs.getString("note"));

				list.add(sale);
			}
			if(list.isEmpty()) {
				errors = new ArrayList<>();
				errors.add("検索結果はありません。");
				session.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/s0020.jsp")
						.forward(req, resp);
				return;
			}
			//JavaBeansをJSPへ渡す
			req.setAttribute("list", list);

			//foward
			getServletContext().getRequestDispatcher("/WEB-INF/s0021.jsp")
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
	private List<String> validate(String saleDate1,String saleDate2,HttpServletRequest req,String accountName) {
		List<String> errors = new ArrayList<>();


		if(!saleDate1.equals("")) {
			try {
				if(LocalDate.parse(saleDate1, DateTimeFormatter.ofPattern("uuuu/M/d")
						.withResolverStyle(ResolverStyle.STRICT)) != null ) {
					LocalDate.parse(saleDate1, DateTimeFormatter.ofPattern("uuuu/M/d")
							.withResolverStyle(ResolverStyle.STRICT)) ;
				}else {
					LocalDate.parse(saleDate1, DateTimeFormatter.ofPattern("uuuu/MM/dd")
							.withResolverStyle(ResolverStyle.STRICT));

				}


			}catch(Exception e) {
				errors.add("販売日（検索開始日）を正しく入力して下さい。");
			}
		}

		if(!saleDate2.equals("")) {
			try {
				if(LocalDate.parse(saleDate2, DateTimeFormatter.ofPattern("uuuu/M/d")
						.withResolverStyle(ResolverStyle.STRICT)) != null ) {
					LocalDate.parse(saleDate2, DateTimeFormatter.ofPattern("uuuu/M/d")
							.withResolverStyle(ResolverStyle.STRICT)) ;
				}else {
					LocalDate.parse(saleDate2, DateTimeFormatter.ofPattern("uuuu/MM/dd")
							.withResolverStyle(ResolverStyle.STRICT));
				}

			}catch(Exception e) {
				errors.add("販売日（検索終了日）を正しく入力して下さい。");
			}
		}

		if(!req.getParameter("sale_date2").equals("") && !req.getParameter("sale_date1").equals("")) {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
			fmt.setLenient(false);
			try {
				java.util.Date f1 = fmt.parse(req.getParameter("sale_date1"));
				java.util.Date f2 = fmt.parse(req.getParameter("sale_date2"));
				if(f1.after(f2)) {
					errors.add("販売日（検索開始日)が販売日（検索終了日)より後の日付になっています。");
				}
			}catch(Exception e1) {

			}
		}

		return errors;



	}

}
