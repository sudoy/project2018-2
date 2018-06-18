<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.abc.asms.utils.HtmlUtils" %>

<c:set var="data" value="${today}" />

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

	<jsp:include page="_errors.jsp" />


		<h1 class="text-center">ダッシュボード</h1>

		<div class="row">
			<div class="col-sm-2">
				<nav class="float-left">
					<ul class="pagination">
						<li class="page-item">
							<a class="page-link" href="C0020.html?before=${data}"><span class="oi oi-chevron-left"></span><span class="oi oi-chevron-left"></span> 前年</a>
						</li>
						<li class="page-item">
							<a class="page-link" href="C0020.html?back=${data}"><span class="oi oi-chevron-left"></span> 前月</a>
						</li>
					</ul>
				</nav>
			</div>

			<div class="col-sm-8 text-center">
				<h2>${data}</h2>
			</div>

			<div class="col-sm-2" style="padding-left: 33px;">
				<nav class="float-right">
					<ul class="pagination">
						<li class="page-item">
							<a class="page-link" href="C0020.html?next=${data}">翌月 <span class="oi oi-chevron-right"></span></a>
						</li>
						<li class="page-item">
							<a class="page-link" href="C0020.html?after=${data}">翌年 <span class="oi oi-chevron-right"></span><span class="oi oi-chevron-right"></span></a>
						</li>
					</ul>
				</nav>
			</div>
		</div>


	<div class="row">
		<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading">
			<h3 class="panel-title">前月（5月）の売上合計</h3>
			</div>
			<div class="panel-body" align="center">${HtmlUtils.formatComma(lastMonthSum)}</div>
		</div>
		</div>


		<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading">
			<h3 class="panel-title">今月（6月）の売上合計</h3>
			</div>
			<div class="panel-body" align="center">${HtmlUtils.formatComma(thisMonthSum)}</div>
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
			<div class="panel-heading">今月の${accounts.name}さんの売上</div>
			<div class="panel-body">
				<table class="table">
					<thead>
						<tr>
							<th style="text-align: right">No</th>
							<th>販売日</th>
							<th>商品カテゴリー</th>
							<th>商品名</th>
							<th style="text-align: right">単価</th>
							<th style="text-align: right">個数</th>
							<th style="text-align: right">小計</th>
						</tr>
					</thead>
					<c:forEach var="c0020" items="${list}">
						<tr>
							<td style="text-align: right">${c0020.saleId}</td>
							<td>${c0020.saleDate}</td>
							<td>${c0020.categoryName}</td>
							<td>${c0020.tradeName}</td>
							<td style="text-align: right">${HtmlUtils.formatComma(c0020.unitPrice)}</td>
							<td style="text-align: right">${HtmlUtils.formatComma(c0020.saleNumber)}</td>
							<td style="text-align: right">${HtmlUtils.formatComma(c0020.unitPrice * c0020.saleNumber)}</td>
						</tr>
					</c:forEach>

						<tr>
							<td>　</td>
							<td>　</td>
							<td>　</td>
							<td>　</td>
							<td>　</td>
							<th style="text-align: right">合計</th>
							<td style="text-align: right">${HtmlUtils.formatComma(thisMonthSum)}</td>
						</tr>

				</table>
			</div>
		</div>

	</div><!-- /container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>