<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="../../favicon.ico">

		<title>ログイン｜物品売上管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/signin.css" rel="stylesheet">


	</head>

	<body>

		<div class="container">

			<form class="form-signin"  action="C0010.html" method="post">
				<h2 class="form-signin-heading">物品売上管理システム</h2>
				<jsp:include page="_successes.jsp" />
				<jsp:include page="_errors.jsp" />

				<label for="inputEmail" class="sr-only"></label>
				<input type="text" id="inputEmail" name="mail" class="form-control" placeholder="メールアドレス" value="${param.mail != null ? param.mail : ''}"/>
				<label for="inputPassword" class="sr-only"></label>
				<input type="password" id="inputPassword" name="password" class="form-control" placeholder="パスワード" />

				<input type="submit" class="btn btn-primary" style="width:300px;" value="ログイン"><br>
				<a href="S0045.html" class="btn btn-link">パスワードを忘れた方はこちら</a>


			</form>

		</div> <!-- /container -->

	</body>
</html>