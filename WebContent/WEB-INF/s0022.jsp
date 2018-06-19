<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.abc.asms.utils.HtmlUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#global-nav"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="C0020.html">物品売上管理システム</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="C0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
					<li><a href="S0010.html">売上登録</a></li>
					<li class="active"><a href="S0020.html">売上検索</a></li>
					<li><a href="S0030.html">アカウント登録</a></li>
					<li><a href="S0040.html">アカウント検索</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="C0010.html">ログアウト</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<form class="form-horizontal" action="S0025.html?id=${s22.sale_id}"
		method="post">

		<div class="container">
			<jsp:include page="_successes.jsp" />

			<h1>売上詳細表示</h1>

			<div class="col-sm-offset-2">
				<table>
					<tr>
						<th>販売日</th>
						<td>${HtmlUtils.formatDate(s22.sale_date)}</td>
					</tr>

					<th>　</th>

					<tr>
						<th>担当</th>
						<td>${s22.name}</td>
					</tr>

					<th>　</th>

					<tr>
						<th>商品カテゴリー</th>
						<td>${s22.category_name}</td>
					</tr>

					<th>　</th>

					<tr>
						<th>商品名</th>
						<td>${s22.trade_name}</td>
					</tr>

					<th>　</th>

					<tr>
						<th>単価</th>
						<td class=text-right>${HtmlUtils.formatComma(s22.unit_price)}</td>
					</tr>

					<th>　</th>

					<tr>
						<th>個数</th>
						<td class=text-right>${HtmlUtils.formatComma(s22.sale_number)}</td>
					</tr>

					<th>　</th>

					<tr>
						<th>小計</th>
						<td class=text-right>${HtmlUtils.formatComma(s22.sum)}</td>
					</tr>

					<th>　</th>

					<tr>
						<th>備考</th>
						<td>${s22.note}</td>
					</tr>

					<th>　</th>

				</table>
			</div>

			<div class="col-sm-offset-3">

				<c:if
					test="${accounts.authority == '1' || accounts.authority == '11'}">
					<a href="S0023.html?id=${s22.sale_id}" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"> 編 集</span></a>
					<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true">削 除</span></button>
				</c:if>
				<a href="S0021.html" class="btn btn-default">キャンセル</a>

			</div>
		</div>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</form>
</body>
</html>