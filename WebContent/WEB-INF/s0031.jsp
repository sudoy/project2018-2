<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.abc.asms.utils.AuthorityUtils"%>
<%@page import="com.abc.asms.utils.HtmlUtils" %>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>アカウント登録確認｜物品売上管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">

		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#global-nav" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="C0020.html">物品売上管理システム</a>
				</div>

				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="C0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
						<li><a href="S0010.html">売上登録</a></li>
						<li><a href="S0020.html">売上検索</a></li>
						<li class="active"><a href="S0030.html">アカウント登録</a></li>
						<li><a href="S0040.html">アカウント検索</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">${accounts.name} 様</a></li>
						<li><a href="C0030.html">ログアウト</a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div class="container">

			<h1>アカウントを登録してよろしいですか？</h1>

			<form class="form-horizontal" action="S0031.html" method="post">
				<input type="hidden" name="name" value="${ia.name}">
				<div class="form-group">
		 			<label class="col-sm-3 control-label">氏名 <span class="badge">必須</span></label>
		 			<div class="col-sm-5">
						<input type="text" class="form-control" name="name"  value="${ia.name}" disabled>
					</div>
				</div>
				<input type="hidden" name="mail" value="${ia.mail}">
				<div class="form-group">
		 			<label class="col-sm-3 control-label">メールアドレス <span class="badge">必須</span></label>
		 			<div class="col-sm-5">
						<input type="text" class="form-control" name="mail" value="${ia.mail}" disabled>
					</div>
				</div>
				<input type="hidden" name="password1" value="${ia.password1}">
				<div class="form-group">
		 			<label class="col-sm-3 control-label">パスワード<span class="badge">必須</span></label>
		 			<div class="col-sm-5">
						<input type="password" class="form-control" name="password1" value="${ia.password1}" disabled>
					</div>
				</div>
				<input type="hidden" name="password2" value="${ia.password2}">
				<div class="form-group">
		 			<label class="col-sm-3 control-label">パスワード（確認）<span class="badge">必須</span></label>
		 			<div class="col-sm-5">
						<input type="password" class="form-control" name="password2" value="${ia.password2}" disabled>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label">売上登録権限 <span class="badge">必須</span></label>
						<div class="col-sm-5">
							<label><input type="radio" name="authority1" value="0" ${ia.authority1 eq 0 ? 'checked' : ''} onclick="return false;">権限なし</label>
							<label><input type="radio" name="authority1" value="1" ${ia.authority1 eq 1 ? 'checked' : ''} onclick="return false;">権限あり</label>
						</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label">アカウント登録権限 <span class="badge">必須</span></label>
						<div class="col-sm-5">
							<label><input type="radio" name="authority2" value="0" ${ia.authority2 eq 0 ? 'checked' : ''} onclick="return false;">権限なし</label>
							<label><input type="radio" name="authority2" value="1" ${ia.authority2 eq 1 ? 'checked' : ''} onclick="return false;">権限あり</label>
						</div>
				</div>

				<div class="col-sm-4 text-right">
					<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> O K</button>
				</div>

			</form>

			<form action="S0030.html" method="POST">
				<div class="col-sm-4 text-left">
					<button type="submit" name="cancel" class="btn btn-default">キャンセル</button>
				</div>
			</form>

		</div>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>