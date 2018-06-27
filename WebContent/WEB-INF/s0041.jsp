<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.abc.asms.utils.AuthorityUtils"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>アカウント検索結果一覧｜物品売上管理システム</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
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
					<li><a href="S0020.html">売上検索</a></li>
					<li><a href="S0030.html">アカウント登録</a></li>
					<li class="active"><a href="S0040.html">アカウント検索</a></li>

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">${accounts.name} 様</a></li>
					<li><a href="C0030.html">ログアウト</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<jsp:include page="_successes.jsp" />

		<h1>アカウント検索結果一覧</h1>

		<table class="table">
			<tr>
				<th>操作</th>
				<th style="text-align: right">No</th>
				<th>氏名</th>
				<c:if test="${accounts.authority == '10' || accounts.authority == '11'}">
				<th>メールアドレス</th>
				</c:if>
				<th>権限</th>

			</tr>

			<c:forEach var="account" items="${list}">
				<tr>
					<td><c:if
							test="${accounts.authority == '10' || accounts.authority == '11'}">
							<a href="S0042.html?account_id=${account.accountId}"
								class="btn btn-primary"><span class="glyphicon glyphicon-ok"
								aria-hidden="true"></span> 編集</a>
							<a href="S0044.html?account_id=${account.accountId}"
								class="btn btn-danger"><span
								class="glyphicon glyphicon-remove" aria-hidden="true"></span> 削除</a>
						</c:if></td>
					<td style="text-align: right">${account.accountId}</td>
					<td>${account.name}</td>
					<c:if test="${accounts.authority == '10' || accounts.authority == '11'}">
					<td>${account.mail}</td>
					</c:if>
					<td>${AuthorityUtils.convertAuthority(account.authority)}</td>
				</tr>
			</c:forEach>

		</table>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>