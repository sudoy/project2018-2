<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.abc.asms.utils.HtmlUtils" %>


<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>売上登録確認｜物品売上管理システム</title>
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
						<li class="active"><a href="S0010.html">売上登録</a></li>
						<li><a href="S0020.html">売上検索</a></li>
						<li><a href="S0030.html">アカウント登録</a></li>
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

			<h1>売上を登録してよろしいですか？</h1>

			<form class="form-horizontal" action="S0011.html" method="post">

				<input type="hidden" name="saleDate" value="${is.saleDate}">
		 		<div class="form-group">
			 		<label for="saleDate" class="col-sm-2 control-label">販売日</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" name="saleDate" id="saleDate" value="${is.saleDate}" disabled>
					</div>
				</div>

				<input type="hidden" name="accountId" value="${is.accountId}">
				<div class="form-group">
			 		<label for="accountId" class="col-sm-2 control-label">担当</label>
			 		<div class="col-sm-5">
						<select class="form-control" name="accountId" id="accountId" disabled>
							<c:forEach var="account" items="${list2}">
								<option value="${accountId}" ${account.accountId eq is.accountId ? 'selected' : ''}>${HtmlUtils.formName(account.name)}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<input type="hidden" name="categoryId" value="${is.categoryId}">
				<div class="form-group">
		 			<label for="categoryId" class="col-sm-2 control-label">商品カテゴリー</label>
		 			<div class="col-sm-5">
		 				<c:forEach var="category" items="${list1}">
							<label><input type="radio"  name="categoryId" value="${categoryId}" ${category.categoryId eq is.categoryId ? 'checked' : ''} disabled>${HtmlUtils.formName(category.categoryName)}</label>
						</c:forEach>
					</div>
				</div>

				<input type="hidden" name="tradeName" value="${is.tradeName}">
				<div class="form-group">
		 			<label for="tradeName" class="col-sm-2 control-label">商品名</label>
		 			<div class="col-sm-5">
						<input type="text" class="form-control" name="tradeName" id="tradeName" value="${is.tradeName}" disabled>
					</div>
				</div>

				<input type="hidden" name="unitPrice" value="${is.unitPrice}">
				<div class="form-group">
		 			<label for="unitPrice" class="col-sm-2 control-label">単価</label>
		 			<div class="col-sm-2">
						<input type="text" style="text-align: right" class="form-control" name="unitPrice" id="unitPrice" value="${HtmlUtils.formatComma(is.unitPrice)}" disabled >
					</div>
				</div>

				<input type="hidden" name="saleNumber" value="${is.saleNumber}">
				<div class="form-group">
		 			<label for="number" class="col-sm-2 control-label">個数</label>
		 			<div class="col-sm-2">
						<input type="text" style="text-align: right" class="form-control" name="saleNumber" id="saleNumber" value="${HtmlUtils.formatComma(is.saleNumber)}" disabled>
					</div>
				</div>

				<input type="hidden" name="subtotal" value="${is.unitPrice * is.saleNumber}">
				<div class="form-group">
		 			<label for="subtotal" class="col-sm-2 control-label">小計（税込）</label>
		 			<div class="col-sm-2">
						<input type="text" style="text-align: right" class="form-control" name="subtotal" id="subtotal" value="${HtmlUtils.formatComma(HtmlUtils.taxPrice1(is.unitPrice, is.saleNumber, is.saleDate))}" disabled>
					</div>
				</div>

				<input type="hidden" name="note" value="${is.note}">
				<div class="form-group">
		 			<label for="note" class="col-sm-2 control-label">備考</label>
		 			<div class="col-sm-5">
						<textarea class="form-control" name="note" id="note" rows="5" disabled placeholder="備考">${is.note}</textarea>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-4">
						<button type="submit" name="insert" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> O K</button>
						<a href="S0010.html"class="btn btn-default">キャンセル</a>
					</div>
				</div>
			</form>
		</div>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>