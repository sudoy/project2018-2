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

		<title>新パスワード入力｜物品売上管理システム</title>

		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>

		<div class="container">
		<div class="container" style="width:600px">
			<jsp:include page="_errors.jsp" />
		</div>
			<form class="form-signin" action="S0046.html?user=${param.user}" method="post">
				<h2 class="form-signin-heading">物品売上管理システム</h2>
				<h3 class="form-signin-heading">新パスワード入力</h3>
				<input type="password" name="password1" id="newPasword" class="form-control" placeholder="新パスワード" >
				<input type="password" name="password2" id="rnewPassword" class="form-control" placeholder="新パスワード確認">

				<button type="submit" class="btn btn-primary" style="width:300px;">
					<span class="glyphicon glyphicon" aria-hidden="true">変更</span>
				</button>

			</form>

		</div>

		<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>

	</body>
</html>