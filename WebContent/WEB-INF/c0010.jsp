<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="../../favicon.ico">

		<title>ログイン｜物品売上管理システム</title>

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

			<form class="form-signin"  action="#" method="post">
				<h2 class="form-signin-heading">物品売上管理システム</h2>
				<jsp:include page="_errors.jsp" />
				<label for="inputEmail" class="sr-only"></label>
				<input type="email" id="inputEmail" name="mail" class="form-control" placeholder="メールアドレス" />
				<label for="inputPassword" class="sr-only"></label>
				<input type="password" id="inputPassword" name="password" class="form-control" placeholder="パスワード" />
				<div class="checkbox">
				</div>


				<input type="submit" class="btn btn-primary" style="width:300px;" value="ログイン"><br>
				<a href="S0045.html" class="btn btn-link">パスワードを忘れた方はこちら</a>

				<!--
				<button class="btn btn-lg btn-primary" type="submit">ログイン</button>
				 <button class="btn btn-link" type="submit">パスワードを忘れた方はこちら</button>
				 -->

			</form>

		</div> <!-- /container -->


		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
	</body>
</html>