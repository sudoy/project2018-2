package com.abc.asms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.asms.utils.DBUtils;

@WebServlet("/S0045.html")
public class S0045Servlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/s0045.jsp")
				.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		HttpSession session = req.getSession();
		String mail = req.getParameter("mail");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;

		//バリデーションチェック
		List<String> errors = validate(mail);
		if (errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/s0045.jsp")
					.forward(req, resp);
			return;
		}

		try {
			try {
				con = DBUtils.getConnection();
				sql = "SELECT * FROM accounts WHERE mail = ?";
				ps = con.prepareStatement(sql);

				ps.setString(1, mail);

				rs = ps.executeQuery();

				//1-4アカウントテーブル存在チェック
//				if (!rs.next()) {
//					errors.add("メールアドレスを正しく入力して下さい。");
//					session.setAttribute("errors", errors);
//					getServletContext().getRequestDispatcher("/WEB-INF/s0045.jsp")
//							.forward(req, resp);
//					return;
//				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			} finally {
				try {
					DBUtils.close(rs);
					DBUtils.close(ps);
					DBUtils.close(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			//メール送信機能

			try {
				// GmailのSMTPを使用する
				Properties property = new Properties();
				property.put("mail.smtp.host", "smtp.gmail.com");
				property.put("mail.smtp.auth", "true");
				property.put("mail.smtp.starttls.enable", "true");
				property.put("mail.smtp.host", "smtp.gmail.com");
				property.put("mail.smtp.port", "587");
				property.put("mail.smtp.debug", "true");

				Session sessionmail = Session.getInstance(property, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("sie.tsd2018@gmail.com", "!sie.tsd2018");
					}
				});

				// toアドレス
				InternetAddress toAddress = new InternetAddress(mail);
				// fromアドレス
				InternetAddress fromAddress = new InternetAddress("sie.tsd2018@gmail.com", "物品売上管理システム");

				MimeMessage mimeMessage = new MimeMessage(sessionmail);
				mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);
				mimeMessage.setFrom(fromAddress);
				mimeMessage.setSubject("【物品売上管理システム】パスワード再設定", "ISO-2022-JP");
				mimeMessage.setText("パスワードの再設定を行います。"
						+ "以下のURLより新パスワードの入力・変更を行って下さい。"
						+ "http://localhost:8080/project2/S0046.html?user=" + mail, "ISO-2022-JP");

				Transport.send(mimeMessage);

				//1-5メール送信エラー
			} catch (AuthenticationFailedException e) {
				errors.add("予期しないエラーが発生しました。");
				session.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/s0045.jsp")
						.forward(req, resp);
				return;
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		//成功メッセージ
		List<String> successes = new ArrayList<>();
		successes.add("パスワード再設定メールを送信しました。");
		session.setAttribute("successes", successes);

		getServletContext().getRequestDispatcher("/WEB-INF/s0045.jsp")
				.forward(req, resp);
	}

	private List<String> validate(String mail) {
		List<String> errors = new ArrayList<>();
		//1-1メールアドレスの必須入力
		if (mail.equals("")) {
			errors.add("メールアドレスを入力して下さい。");
		}
		//1-2メールアドレス長さチェック
		if (mail.length() >= 101) {
			errors.add("メールアドレスが長すぎます。");
		}
		//1-3メールアドレス形式チェック
//		if (!mail.contains("@") 
//				&& !mail.equals("") 
//				&& !mail.substring(0, 1).equals("[0-9a-zA-Z]")
//				&& !mail.substring(1, ).equals("[0-9a-zA-Z]")
//				) {
//			errors.add("メールアドレスを正しく入力して下さい。");
//		}
//		System.out.println(mail.substring(1));

		String pattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]";
		Pattern p = Pattern.compile(pattern);
		
		if(!p.matcher(mail).find()){
			errors.add("メールアドレスを正しく入力して下さい。");
		}
		String key = "@";
		 
		int result = mail.indexOf(key);
 
		if (result != -1) {
			System.out.println(key + "が見つかった位置：" + result);
		} 
		System.out.println(mail.substring(6));
		
		if(!p.matcher(mail).find() && !mail.substring(6).contains(".")){
			errors.add("メールアドレスを正しく入力して下さい。");
		}
		return errors;
	}
}
