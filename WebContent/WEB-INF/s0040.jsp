<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>アカウント検索｜物品売上管理システム</title>
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
				<li><a href="#">${accounts.name}</a></li>
				<li><a href="C0030.html">ログアウト</a></li>
			</ul>
		</div>
	</div>
</nav>

	<div class="container">

		<h1>アカウント検索</h1>
<jsp:include page="_errors.jsp" />
	<form class="form-horizontal" action="S0040.html" method="post">

		<div class="form-group">
 			<label for="name" class="col-sm-2 control-label">氏名 <span class="badge">部分一致</span></label>
 			<div class="col-sm-5">
				<input type="text" class="form-control" id="name" placeholder="氏名" value="${sa.accountName != null ? sa.accountName :accountName}"  name="name">
			</div>
		</div>

		<div class="form-group">
 			<label for="name" class="col-sm-2 control-label">メールアドレス <span class="badge">部分一致</span></label>
 			<div class="col-sm-5">
				<input type="text" class="form-control" id="mailadress" placeholder="メールアドレス" value="${sa.mail != null ? sa.mail :mail}" name="mail">
			</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">売上登録権限</label>
				<div class="col-sm-5">
					<label><input type="radio" name="sauthority" value="all" ${sa.saleAuthority != 'all' ? 'checked' : 'checked'}>全て</label>
					<label><input type="radio" name="sauthority" value="no" ${sa.saleAuthority eq 'no' ? 'checked' : ''}>権限なし</label>
					<label><input type="radio" name="sauthority" value="ok" ${sa.saleAuthority eq 'ok' ? 'checked' : ''}>権限あり</label>
				</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">アカウント登録権限</label>
				<div class="col-sm-5">
					<label><input type="radio" name="aauthority" value="all" ${sa.accountAuthority != 'all' ? 'checked' : 'checked'}>全て</label>
					<label><input type="radio" name="aauthority" value="no"${sa.accountAuthority eq 'no' ? 'checked' : ''}>権限なし</label>
					<label><input type="radio" name="aauthority" value="ok"${sa.accountAuthority eq 'ok' ? 'checked' : ''}>権限あり</label>
				</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-3">
				<button type="submit" class="btn btn-primary "><span class="glyphicon glyphicon-search"></span> 検 索</button>
				<a href="S0040.html" class="btn btn-default">クリア</a>

		</div>
		</div>

	</form>
	</div><!-- /container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>