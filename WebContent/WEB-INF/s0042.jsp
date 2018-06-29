<%@page contentType="text/html; charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="com.abc.asms.utils.AuthorityUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.abc.asms.utils.HtmlUtils"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>アカウント編集｜物品売上管理システム</title>
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
					<c:if test="${accounts.authority == '1' || accounts.authority == '11'}">
						<li><a href="S0010.html">売上登録</a></li>
						</c:if>
						<li><a href="S0020.html">売上検索</a></li>
						<c:if test="${accounts.authority == '10' || accounts.authority == '11'}">
						<li><a href="S0030.html">アカウント登録</a></li>
						</c:if>
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

		<jsp:include page="_errors.jsp" />

		<h1>アカウント編集</h1>

		<form class="form-horizontal"
			action="S0042.html?account_id=${ea.accountId != null ? ea.accountId : accountsList.accountId}"
			method="post">

			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">氏名 <span
					class="badge">必須</span></label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="name" name="name"
						placeholder="氏名" value="${ea.name != null ? HtmlUtils.formName(ea.name) : HtmlUtils.formName(accountsList.name)}">
				</div>
			</div>

			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">メールアドレス <span
					class="badge">必須</span></label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="mailadress" name="mail"
						placeholder="メールアドレス" value="${ea.mail != null ? HtmlUtils.formName(ea.mail) : HtmlUtils.formName(accountsList.mail)}">
				</div>
			</div>

			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">パスワード</label>
				<div class="col-sm-5">
					<input type="password" class="form-control" name="password"
						placeholder="パスワード" id="pass1" value="">
				</div>

			</div>

			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">パスワード（確認）</label>
				<div class="col-sm-5">
					<input type="password" class="form-control" name="passwordc"
						placeholder="パスワード（確認）" id="pass2" value="">
				</div>
			</div>

			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">売上登録権限 <span
					class="badge">必須</span></label>
				<div class="radio">
					<label><input type="radio" name="authority1" value="0"
						${AuthorityUtils.checkAuthority1(ea.authority1 != null ? ea.authority1 : accountsList.authority)}>権限なし</label>
					<label><input type="radio" name="authority1" value="1"
						${AuthorityUtils.checkAuthority2(ea.authority1 != null ? ea.authority1 : accountsList.authority)}>権限あり</label>
				</div>
			</div>

			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">アカウント登録権限 <span
					class="badge">必須</span></label>
				<div class="radio">
					<label><input type="radio" name="authority2" value="0"
						${AuthorityUtils.checkAuthority3(ea.authority2 != null ? ea.authority2 : accountsList.authority)}>権限なし</label>
					<label><input type="radio" name="authority2" value="10"
						${AuthorityUtils.checkAuthority4(ea.authority2 != null ? ea.authority2 : accountsList.authority)}>権限あり</label>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-3">
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-ok" aria-hidden="true"> 更新</span>
					</button>
					<a href="S0041.html" class="btn btn-default">キャンセル</a>
				</div>
			</div>

		</form>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>