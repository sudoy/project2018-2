<%@page contentType="text/html; charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="com.abc.asms.utils.AuthorityUtils"%>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>アカウント編集確認｜物品売上管理システム</title>
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
				<li><a href="S0030.html">アカウント登録</a></li>
				<li class="active"><a href="S0040.html">アカウント検索</a></li>
				
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="C0010.html">ログアウト</a></li>
			</ul>
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid -->
</nav>

	<div class="container">
	
		<h1>アカウントを編集してよろしいですか？</h1>
			<form class="form-horizontal" action="S0043.html?account_id=${param.account_id != null ? param.account_id : accounts.account_id}" method="post">
		<div class="form-group">
 			<label for="name" class="col-sm-3 control-label">氏名 <span class="badge">必須</span></label>
 			<div class="col-sm-5">
				<input type="text" class="form-control" id="name" name="name" value="${param.name}" readonly>
			</div>
		</div>	
		
		<div class="form-group">
 			<label for="name" class="col-sm-3 control-label">メールアドレス <span class="badge">必須</span></label>
 			<div class="col-sm-5">
				<input type="text" class="form-control" id="mailadress" name="mail" value="${param.mail}" readonly>
			</div>
		</div>
		
		<div class="form-group">
 			<label for="name" class="col-sm-3 control-label">パスワード</label>
 			<div class="col-sm-5">
				<input type="password" class="form-control" name="password" id="pass1" value="${param.password}" readonly>
			</div>
			
		</div>	
		
		<div class="form-group">
 			<label for="name" class="col-sm-3 control-label">パスワード（確認）</label>
 			<div class="col-sm-5">
				<input type="password" class="form-control" name="password" id="pass2" value="${param.password}" readonly>
			</div>
		</div>	

		<div class="form-group">
			<label for="name" class="col-sm-3 control-label">売上登録権限 <span class="badge">必須</span></label>
				<div class="radio">
					<label><input type="radio" name="authority1" value="0" ${AuthorityUtils.checkAuthoritya(param.authority1)} readonly onclick="return false;">権限なし</label>
					<label><input type="radio" name="authority1" value="1" ${AuthorityUtils.checkAuthorityb(param.authority1)} readonly onclick="return false;">権限あり</label>
				</div>
		</div>
		
		<div class="form-group">
			<label for="name" class="col-sm-3 control-label">アカウント登録権限 <span class="badge">必須</span></label>
				<div class="radio">
					<label><input type="radio" name="authority2" value="0" ${AuthorityUtils.checkAuthoritya(param.authority2)} readonly onclick="return false;">権限なし</label>
					<label><input type="radio" name="authority2" value="1" ${AuthorityUtils.checkAuthorityb(param.authority2)} readonly onclick="return false;">権限あり</label>
				</div>	
		</div>

		<div class="form-group">
			<div class="col-sm-offset-3">
				<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"> O K</span></button>
				<a href="S0041.html" class="btn btn-default">キャンセル</a>

			</div>
		</div>

	</form>
	</div><!-- /container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>