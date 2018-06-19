<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="../../favicon.ico">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/signin.css" rel="stylesheet">
	<title>ログイン｜物品売上管理システム</title>

	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>

<body>

	<div class="container">
	
<jsp:include page="_successes.jsp" />
<jsp:include page="_errors.jsp" />
	
		<form class="form-signin" action="S0045.html" method="post">
			<h2 class="form-signin-heading">物品売上管理システム</h2>
			<h3 class="form-signin-heading">パスワード再設定</h3>
			<label for="inputEmail" class="sr-only"></label>
			
			<input type="text" id="inputEmail" name="mail" class="form-control" placeholder="メールアドレス">
			
			<button type="submit" class="btn btn-primary" style="width:300px;">
				<span class="glyphicon glyphicon" aria-hidden="true">メール送信</span>
			</button>
		</form>
	</div> <!-- /container -->
	</body>
</html>