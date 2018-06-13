<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.abc.asms.utils.HtmlUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>売上詳細編集確認｜物品売上管理システム</title>
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

		<h1>売上を編集してよろしいですか？</h1>

	<form class="form-horizontal" action="S0022.html?id=6" method="post">
 		<div class="form-group">
	 		<label for="salesDate" class="col-sm-2 control-label">販売日</label>

			<div class="col-sm-2">
					<input type="text" class="form-control" name="sale_date" value="${param.sale_date}"  disabled>
			</div>
		</div>

		 <div class="form-group">
	 			<label for="person" class="col-sm-2 control-label">担当</label>
	 			<div class="col-sm-5">
					<select name="name" class="form-control" disabled>
						<option value="">${param.name}</option>
					</select>
			</div>
		</div>

		<div class="form-group">
 			<label for="category" class="col-sm-2 control-label">商品カテゴリー</label>
 			<div class="col-sm-5">
 					<select name="category_name" class="form-control"disabled>
						<option value="">${param.category_name}</option>
					</select>
			</div>
		</div>

		<div class="form-group">
 			<label for="name" class="col-sm-2 control-label">商品名</label>
 			<div class="col-sm-2">
				<input type="text" class="form-control" name="trade_name" value="${param.trade_name}" disabled>
			</div>
		</div>

		<div class="form-group">

 			<label for="price" name="unit_price" class="col-sm-2 control-label">単価</label>

 			<div class="col-sm-2">
				<input type="text" class="form-control text-right" name="unit_price" value="${param.unit_price}" disabled>
			</div>
		</div>

		<div class="form-group">
 			<label for="number" class="col-sm-2 control-label">個数</label>
 			<div class="col-sm-2">
				<input type="text" class="form-control text-right" name="sale_number" value="${param.sale_number}" disabled>
			</div>
		</div>

		<div class="form-group">
 			<label for="number" class="col-sm-2 control-label">小計</label>
 			<div class="col-sm-2">
				<input type="text" class="form-control text-right" name="sum" value="${param.unit_price * param.sale_number}" disabled>
			</div>
		</div>


		<div class="form-group">
 			<label for="note" class="col-sm-2 control-label">備考</label>
 			<div class="col-sm-5">
				<textarea class="form-control" name="note" rows="5" disabled>${param.note != null? param.note : null}</textarea>
			</div>
		</div>


		<div class="form-group">
			<div class="col-sm-offset-4">
				<a href="S0024.html?id=${param.id}" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"> O K</span></a>
				<a href="S0023.html?id=${param.id} " class="btn btn-default">キャンセル</a>
			</div>
		</div>
	</form>
	</div><!-- /container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>