<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>アカウント削除確認｜物品売上管理システム</title>
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
	
		<h1>アカウントを削除してよろしいですか？</h1>
		
	<form class="form-horizontal" action="S0044.html?account_id=${param.account_id != null ? param.account_id : accounts.account_id}" method="post">
	
		<div class="form-group">
 			<label for="name" class="col-sm-3 control-label">氏名 <span class="badge">必須</span></label>
 			<div class="col-sm-5">
				<input type="text" class="form-control" id="name" name="name" value="${accounts.name}" disabled>
			</div>
		</div>	
		
		<div class="form-group">
 			<label for="name" class="col-sm-3 control-label">メールアドレス <span class="badge">必須</span></label>
 			<div class="col-sm-5">
				<input type="text" class="form-control" id="mailadress" name="mail" value="${accounts.mail}" disabled>
			</div>
		</div>
		
		<div class="form-group">
 			<label for="name" class="col-sm-3 control-label">パスワード</label>
 			<div class="col-sm-5">
				<input type="password" class="form-control" name="password" id="pass1" value="" disabled>
			</div>
			
		</div>	
		
		<div class="form-group">
 			<label for="name" class="col-sm-3 control-label">パスワード（確認）</label>
 			<div class="col-sm-5">
				<input type="password" class="form-control" name="pass2" id="pass2" value="" disabled>
			</div>
		</div>	

		<div class="form-group">
			<label for="name" class="col-sm-3 control-label">売上登録権限 <span class="badge">必須</span></label>
				<div class="radio">
					<label><input type="radio" name="authority" value="0" ${AuthorityUtils.checkAuthority1(account.authority)} disabled>権限なし</label>
					<label><input type="radio" name="authority" value="1" ${AuthorityUtils.checkAuthority2(account.authority)} disabled>権限あり</label>
				</div>
		</div>
		
		<div class="form-group">
			<label for="name" class="col-sm-3 control-label">アカウント登録権限 <span class="badge">必須</span></label>
				<div class="radio">
					<label><input type="radio" name="authority1" value="0" ${AuthorityUtils.checkAuthority3(account.authority)} disabled>権限なし</label>
					<label><input type="radio" name="authority1" value="1" ${AuthorityUtils.checkAuthority4(account.authority)} disabled>権限あり</label>
				</div>	
		</div>

		<div class="form-group">
			<div class="col-sm-offset-4">
				<input type="submit" class="btn btn-danger" value=" O K">
				<a href="S0041.html" class="btn btn-default">キャンセル</a>

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