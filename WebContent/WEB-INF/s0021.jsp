<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.abc.asms.utils.HtmlUtils" %>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>売上検索結果表示｜物品売上管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
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
				<li ><a href="C0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
				<li><a href="S0010.html">売上登録</a></li>
				<li class="active"><a href="S0020.html">売上検索</a></li>
				<li><a href="S0030.html">アカウント登録</a></li>
				<li><a href="S0040.html">アカウント検索</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="C0030.html">ログアウト</a></li>
			</ul>
		</div>
	</div>
</nav>

	<div class="container">
	<jsp:include page="_successes.jsp"/>

		<h1>売上検索結果表示</h1>

		<table class="table">
			<tr>
				<th>操作</th>
				<th>No</th>
				<th>販売日</th>
				<th>担当</th>
				<th>商品カテゴリー</th>
				<th>商品名</th>
				<th>単価</th>
				<th>個数</th>
				<th>小計</th>
			</tr>
			<c:forEach var="project2" items="${list}">
			<tr>
			<td><a href="S0022.html?id=${project2.saleId}" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細</a></td>
			<td>${project2.saleId}</td>
			<td>${HtmlUtils.formDate(project2.saleDate)}</td>
			<td>${project2.accountName}</td>
			<td>${project2.categoryName}</td>
			<td>${project2.tradeName}</td>
			<td>${HtmlUtils.formatComma(project2.unitPrice)}</td>
			<td>${HtmlUtils.formatComma(project2.saleNumber)}</td>
			<td>${HtmlUtils.formatComma(project2.unitPrice * project2.saleNumber)}</td>
			</tr>
			</c:forEach>



		</table>
	</div><!-- /container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>