<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>売上詳細表示｜物品売上管理システム</title>
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
				<li ><a href="C0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
				<li><a href="S0010.html">売上登録</a></li>
				<li class="active"><a href="S0020.html">売上検索</a></li>
				<li><a href="S0030.html">アカウント登録</a></li>
				<li><a href="S0040.html">アカウント検索</a></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="C0010.html">ログアウト</a></li>
			</ul>
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid -->
</nav>

	<div class="container">

		<h1>売上詳細表示</h1>

	<div class="col-sm-offset-2">
		<table>
			<tr>
				<th>販売日</th>
				<td>2015/1/15</td>
			</tr>

			<th>　</th>

			<tr>
				<th>担当</th>
				<td>イチロー</td>
			</tr>

			<th>　</th>

			<tr>
				<th>商品カテゴリー</th>
				<td>食料品</td>
			</tr>

			<th>　</th>

			<tr>
				<th>商品名</th>
				<td>からあげ弁当</td>
			</tr>

			<th>　</th>

			<tr>
				<th>単価</th>
				<td>450</td>
			</tr>

			<th>　</th>

			<tr>
				<th>個数</th>
				<td>3</td>
			</tr>

			<tr>
				<th>小計</th>
				<td>1,250</td>
			</tr>

			<tr>
				<th>備考</th>
				<td>今日からの新商品</td>
			</tr>

			<th>　</th>

		</table>
		</div>

		<div class="col-sm-offset-3">
			<a href="S0023.html" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"> 編　集</span></a>
			<a href="S0025.html" class="btn btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true"> 削　除</span></a>
			<a href="S0021.html" class="btn btn-default">キャンセル</a><!-- キャンセル先は暫定。 -->

		<!--
		<button type="button" class="btn btn-default">Default</button>

		<button type="button" class="btn btn-primary">Primary</button>

		<button type="button" class="btn btn-success">Success</button>
		-->

		</div>
	</div><!-- /container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>