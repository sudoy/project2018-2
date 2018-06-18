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

		<title>新パスワード入力｜物品売上管理システム</title>

		<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

		<link href="css/signin.css" rel="stylesheet">

		<script type="text/javascript" src="http://gc.kis.v2.scr.kaspersky-labs.com/15FFB0EB-D585-DC46-87A9-4A5DB79FD622/main.js" charset="UTF-8"></script><script src="../../assets/js/ie-emulation-modes-warning.js"></script>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>

		<div class="container">
<jsp:include page="_errors.jsp" />

			<form class="form-signin" action="S0046.html?user=${param.user}" method="post">
				<h2 class="form-signin-heading">物品売上管理システム</h2>
				<h3 class="form-signin-heading">新パスワード入力</h3>
				<label for="inputPassword" class="sr-only"></label>
				<input type="password" name="password1" id="newPasword" class="form-control" placeholder="新パスワード" >
				<label for="inputPassword" class="sr-only"></label>
				<input type="password" name="password2" id="rnewPassword" class="form-control" placeholder="新パスワード確認">
				
				<button type="submit" class="btn btn-primary" style="width:300px;"><span class="glyphicon glyphicon" aria-hidden="true">変更</span></button>
				
			</form>

		</div> <!-- /container -->


		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
	</body>
</html>