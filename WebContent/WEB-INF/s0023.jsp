<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.abc.asms.utils.HtmlUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>売上詳細編集｜物品売上管理システム</title>
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
					<li><a href="#">${accounts.name} 様</a></li>
					<li><a href="C0030.html">ログアウト</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<form class="form-horizontal" action="S0024.html?id=${param.id != null? param.id : s23.saleId}"
		method="POST">
		<div class="container">
			<jsp:include page="_errors.jsp" />

			<h1>売上詳細編集</h1>

			<div class="form-group">
				<label for="salesDate" class="col-sm-2 control-label">販売日 <span
					class="badge">必須</span></label>

				<div class="col-sm-2">
					<input type="text" class="form-control" name="sale_date"
						id="salesDate" placeholder="販売日" value="${data.saleDate != null? data.saleDate : HtmlUtils.formDate(s23.saleDate)}">
				</div>
			</div>

			<div class="form-group">
				<label for="person" class="col-sm-2 control-label">担当 <span
					class="badge">必須</span></label>
				<div class="col-sm-5">
					<select class="form-control" name="account_id">
						<option value="0">選択してください</option>
						<c:forEach var="aName" items="${list2}">
							<option value="${aName.accountId}"
								${data.accountId != null? data.accountId eq aName.accountId ? 'selected' : '' : aName.accountId.equals(s23.accountId) ? 'selected' : '' }>${HtmlUtils.formName(aName.name)}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="category" class="col-sm-2 control-label">商品カテゴリー
					<span class="badge">必須</span>
				</label>
				<div class="col-sm-5">
					<c:forEach var="type" items="${list1}">
						<label class="radio-inline"> <input type="radio"
							name="category_id" value="${type.categoryId}"
							${data.categoryId != null? data.categoryId eq type.categoryId ? 'checked' : '' : type.categoryId.equals(s23.categoryId) ? 'checked' : '' }>${HtmlUtils.formName(type.categoryName)}
						</label>
					</c:forEach>
				</div>
			</div>

			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">商品名 <span
					class="badge">必須</span></label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="trade_name" id="name"
						placeholder="商品名" value="${data.tradeName != null? HtmlUtils.formName(data.tradeName) : HtmlUtils.formName(s23.tradeName)}">
				</div>
			</div>

			<div class="form-group">

				<label for="price" class="col-sm-2 control-label">単価 <span
					class="badge">必須</span></label>

				<div class="col-sm-2">
					<input type="text" class="form-control text-right"
						 placeholder="単価" name="unit_price" id="price" value="${data.unitPrice != null? data.unitPrice : s23.unitPrice}">
				</div>
			</div>

			<div class="form-group">
				<label for="number" class="col-sm-2 control-label">個数 <span
					class="badge">必須</span></label>
				<div class="col-sm-2">
					<input type="text" class="form-control text-right"
						placeholder="個数" name="sale_number" id="number" value="${data.saleNumber != null? data.saleNumber : s23.saleNumber}">
				</div>
			</div>

			<div class="form-group">
				<label for="note" class="col-sm-2 control-label">備考 </label>
				<div class="col-sm-5">
					<textarea placeholder="備考" class="form-control" name="note" id="note" rows="5">${data.note != null? HtmlUtils.formName(data.note) : HtmlUtils.formName(s23.note)}</textarea>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-4">
					<button type="submit" name="check" class="btn btn-primary">
						<span class="glyphicon glyphicon-ok" aria-hidden="true"> 更
							新</span>
					</button>
					<a href="S0022.html?id=${data.saleId != null? data.saleId : s23.saleId}" class="btn btn-default">キャンセル</a>
				</div>
			</div>

		</div>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</form>
</body>
</html>