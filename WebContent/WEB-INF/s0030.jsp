<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.abc.asms.utils.AuthorityUtils"%>
<%@page import="com.abc.asms.utils.HtmlUtils" %>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>アカウント登録｜物品売上管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">

		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
<nav class="navbar navbar-default">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#global-nav" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="C0020.html">物品売上管理システム</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="C0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
				<li><a href="S0010.html">売上登録</a></li>
				<li><a href="S0020.html">売上検索</a></li>
				<li class="active"><a href="S0030.html">アカウント登録</a></li>
				<li><a href="S0040.html">アカウント検索</a></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="C0010.html">ログアウト</a></li>
			</ul>
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid -->
</nav>

	<div class="container">

		<h1>アカウント登録</h1>

		<jsp:include page="_errors.jsp" />
		<jsp:include page="_successes.jsp" />


	<form class="form-horizontal" action="S0030.html" method="post">

		<div class="form-group">
 			<label for="name" class="col-sm-3 control-label">氏名 <span class="badge">必須</span></label>
 			<div class="col-sm-5">
				<input type="text" class="form-control" name="name" placeholder="氏名" value="${param.name}">
			</div>
		</div>

		<div class="form-group">
 			<label for="mail" class="col-sm-3 control-label">メールアドレス <span class="badge">必須</span></label>
 			<div class="col-sm-5">
				<input type="text" class="form-control" name="mail" placeholder="メールアドレス" value="${param.mail}">
			</div>
		</div>

		<div class="form-group">
 			<label for="name" class="col-sm-3 control-label">パスワード<span class="badge">必須</span></label>
 			<div class="col-sm-5">
				<input type="text" class="form-control" name="password" placeholder="パスワード" value="">
			</div>

		</div>
		<div class="form-group">
 			<label for="name" class="col-sm-3 control-label">パスワード（確認）<span class="badge">必須</span></label>
 			<div class="col-sm-5">
				<input type="text" class="form-control" name="password2" placeholder="パスワード（確認）" value="">
			</div>
		</div>


		<div class="form-group">
			<label for="name" class="col-sm-3 control-label">売上登録権限 <span class="badge">必須</span></label>
				<div class="col-sm-5">
					<label><input type="radio" name="authority1" value="0" ${AuthorityUtils.checkAuthority1(param.authority1 != null ? param.authority1 : accounts.authority)}>権限なし</label>
					<label><input type="radio" name="authority1" value="1" ${AuthorityUtils.checkAuthority2(param.authority1 != null ? param.authority1 : accounts.authority)}>権限あり</label>
				</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-3 control-label">アカウント登録権限 <span class="badge">必須</span></label>
				<div class="col-sm-5">
					<label><input type="radio" name="authority2" value="0" ${AuthorityUtils.checkAuthority3(param.authority2 != null ? AuthorityUtils.checkAuthority3a(param.authority2) : accounts.authority)}>権限なし</label>
					<label><input type="radio" name="authority2" value="1" ${AuthorityUtils.checkAuthority4(param.authority2 != null ? AuthorityUtils.checkAuthority4a(param.authority2) : accounts.authority)}>権限あり</label>
				</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-4">
				<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 登　録</button>

			<!--
			<button type="button" class="btn btn-primary">O K</button>
			<button type="button" class="btn btn-default">キャンセル</button>
			-->
			</div>
		</div>

	</form>
	</div><!-- /container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>