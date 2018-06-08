<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>ダッシュボード｜物品売上管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="css/style.css" type="text/css">

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
				<li class="active"><a href="C0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
				<li><a href="S0010.html">売上登録</a></li>
				<li><a href="S0020.html">売上検索</a></li>
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

		<h1>ダッシュボード</h1>
	<div class="row">
		<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading">
			<h3 class="panel-title">前月（5月）の売上合計</h3>
			</div>
			<div class="panel-body" align="center">1,000,000円</div>
		</div>
		</div>


		<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading">
			<h3 class="panel-title">今月（6月）の売上合計</h3>
			</div>
			<div class="panel-body" align="center">1,200,000円</div>
		</div>
		</div>


		<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading">
			<h3 class="panel-title">前月比</h3>
			</div>
			<div class="panel-body" align="center"><span class="glyphicon glyphicon-arrow-up" aria-hidden="true">120.00%</span></div>
		</div>
		</div>

		</div>

		<div class="panel panel-default">
			<div class="panel-heading">今月のイチローさんの売上</div>
			<div class="panel-body">
				<table class="table">
					<thead>
						<tr>
							<th>No</th>
							<th>販売日</th>
							<th>商品カテゴリー</th>
							<th>商品名</th>
							<th>単価</th>
							<th>個数</th>
							<th>小計</th>
						</tr>
					</thead>

						<tr>
							<td>1</td>
							<td>2018/5/14</td>
							<td>食料品</td>
							<td>からあげ弁当</td>
							<td>450</td>
							<td>3</td>
							<td>1350</td>
						</tr>

						<tr>
							<td>2</td>
							<td>2018/5/14</td>
							<td>食料品</td>
							<td>あんぱん</td>
							<td>120</td>
							<td>10</td>
							<td>1200</td>
						</tr>

						<tr>
							<td>3</td>
							<td>2018/5/14</td>
							<td>飲料</td>
							<td>コカコーラ 500ml</td>
							<td>130</td>
							<td>5</td>
							<td>650</td>
						</tr>

						<tr>
							<td>　</td>
							<td>　</td>
							<td>　</td>
							<td>　</td>
							<td>　</td>
							<th>合計</th>
							<td>3,000</td>
						</tr>

				</table>
			</div>
		</div>

	</div><!-- /container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>