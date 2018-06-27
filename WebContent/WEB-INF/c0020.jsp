<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.abc.asms.utils.HtmlUtils" %>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>ダッシュボード｜物品売上管理システム</title>

		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="css/style.css" type="text/css">
		<link rel="stylesheet" href="css/open-iconic-bootstrap.min.css" type="text/css">

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
						<c:if test="${accounts.authority == '1' || accounts.authority == '11'}">
						<li><a href="S0010.html">売上登録</a></li>
						</c:if>
						<li><a href="S0020.html">売上検索</a></li>
						<c:if test="${accounts.authority == '10' || accounts.authority == '11'}">
						<li><a href="S0030.html">アカウント登録</a></li>
						</c:if>
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

			<jsp:include page="_errors.jsp" />

			<h1 class="text-center">ダッシュボード</h1>

			<div class="row">
				<div class="col-sm-2">
					<nav class="float-left">
						<ul class="pagination">
							<li class="page-item">
								<a class="page-link" href="C0020.html?before=${today}"><span class="oi oi-chevron-left"></span><span class="oi oi-chevron-left"></span> 前年</a>
							</li>
							<li class="page-item">
								<a class="page-link" href="C0020.html?back=${today}"><span class="oi oi-chevron-left"></span> 前月</a>
							</li>
						</ul>
					</nav>
				</div>

				<div class="col-sm-8 text-center">
					<h2>${today}</h2>
				</div>

				<div class="col-sm-2" style="padding-left: 33px;">
					<nav class="float-right">
						<ul class="pagination">
							<li class="page-item">
								<a class="page-link" href="C0020.html?next=${today}">翌月 <span class="oi oi-chevron-right"></span></a>
							</li>
							<li class="page-item">
								<a class="page-link" href="C0020.html?after=${today}">翌年 <span class="oi oi-chevron-right"></span><span class="oi oi-chevron-right"></span></a>
							</li>
						</ul>
					</nav>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">前月（${lastMonth}月）の売上合計</h3>
						</div>
						<div class="panel-body" align="center" style="text-align: right">${HtmlUtils.formatComma(lastMonthSum)}円</div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">今月（${thisMonth}月）の売上合計</h3>
						</div>
						<div class="panel-body" align="center" style="text-align: right">${HtmlUtils.formatComma(thisMonthSum)}円</div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">前月比</h3>
						</div>
						<div class="panel-body" align="center">
							<c:choose>
								<c:when test="${lastMonthSum eq 0 and  thisMonthSum eq 0}">
									<strong>-</strong>
								</c:when>
								<c:when test="${lastMonthSum eq 0 and  thisMonthSum ge 1}">
									<strong>-</strong>
								</c:when>
								<c:when test="${MoM >= 100}">
									<div style="color: green"><span class="glyphicon glyphicon-arrow-up" aria-hidden="true"><fmt:formatNumber value="${MoM}" pattern="0.00" />%</span></div>
								</c:when>
								<c:otherwise>
									<div style="color: red"><span class="glyphicon glyphicon-arrow-down" aria-hidden="true"><fmt:formatNumber value="${MoM}" pattern="0.00" />%</span></div>
								</c:otherwise>
							</c:choose>
						</div>
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
								<th style="text-align: right">小計（税込）</th>
							</tr>
						</thead>
						<c:set var="total" value="${0}"/>
						<c:forEach var="c0020" items="${list}">
							<tr>
								<td style="text-align: right">${c0020.saleId}</td>
								<td>${HtmlUtils.formDate(c0020.saleDate)}</td>



								<td>${HtmlUtils.formName(c0020.categoryName)}</td>
								<td>${HtmlUtils.formName(c0020.tradeName)}</td>
								<td style="text-align: right">${HtmlUtils.formatComma(c0020.unitPrice)}</td>
								<td style="text-align: right">${HtmlUtils.formatComma(c0020.saleNumber)}</td>
								<td style="text-align: right">${HtmlUtils.formatComma(HtmlUtils.taxPrice(c0020.unitPrice, c0020.saleNumber, c0020.saleDate))}</td>
								
								<c:set var="total" value="${total + HtmlUtils.taxPrice(c0020.unitPrice, c0020.saleNumber, c0020.saleDate)}"/>
							</tr>
						</c:forEach>

							<tr>
								<td>　</td>
								<td>　</td>
								<td>　</td>
								<td>　</td>
								<td>　</td>
								<th style="text-align: right">合計</th>
								<td style="text-align: right">${HtmlUtils.formatComma(total)}</td>
							</tr>

					</table>
				</div>
			</div>
		</div>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>