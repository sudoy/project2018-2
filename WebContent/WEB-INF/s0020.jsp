<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.abc.asms.utils.HtmlUtils" %>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>売上検索条件入力｜物品売上管理システム</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
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
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li ><a href="C0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
				<c:if test="${accounts.authority == '1' || accounts.authority == '11'}">
						<li><a href="S0010.html">売上登録</a></li>
						</c:if>
						<li class="active"><a href="S0020.html">売上検索</a></li>
						<c:if test="${accounts.authority == '10' || accounts.authority == '11'}">
						<li><a href="S0030.html">アカウント登録</a></li>
						</c:if>
				<li><a href="S0040.html">アカウント検索</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">${accounts.name}</a></li>
				<li><a href="C0030.html">ログアウト</a></li>
			</ul>
		</div>
	</div>
</nav>

	<div class="container">

		<h1>売上検索条件入力</h1>
<jsp:include page="_errors.jsp" />
	<form class="form-horizontal" action="S0020.html" method="post">
 		<div class="form-group">
	 		<label class="col-sm-2 control-label">販売日 </label>
			<div class="col-sm-2">
				<input type="text" class="form-control" name="sale_date1" id="salesspanStart" placeholder="販売日(検索開始日)"  value="${ss.saleDate1 != null ? ss.saleDate1 : today}" >
			</div>

			<div class="col-xs-1" align="center">
				<h5>～</h5>
			</div>

			<div class="col-sm-2">
				<input type="text" class="form-control" name="sale_date2" id="salesspanEnd" placeholder="販売日(検索終了日)"  value="${ss.saleDate2 != null ? ss.saleDate2 : today}">
			</div>

		</div>

		 <div class="form-group">
	 			<label class="col-sm-2 control-label">担当 </label>
	 			<div class="col-sm-5">
					<select class="form-control" name="name" >
						<option value="" >選択してください</option>
						<c:forEach var="staff" items="${list2}">
						<option value="${staff.name}" ${ss.accountName eq staff.name  ? 'selected' : ''}>${HtmlUtils.formName(staff.name)}</option>
						</c:forEach>
					</select>
				</div>
		 </div>



		<div class="form-group">
 			<label class="col-sm-2 control-label">商品カテゴリー</label>
 			<div class="col-sm-5">
 			<c:forEach  var="product" items="${list1}">

	 			<label ><input type="checkbox" name="category_name" value="${product.categoryName}" ${HtmlUtils.checkCategory(paramValues.categoryName, product.categoryName) ? 'checked' : ''}>${HtmlUtils.formName(product.categoryName)}</label>
			</c:forEach>
			</div>
		</div>

		<div class="form-group">
 			<label class="col-sm-2 control-label">商品名 <span class="badge">部分一致</span></label>
 			<div class="col-sm-5">
				<input type="text" class="form-control" name="trade_name" id="name" placeholder="商品名" value="${ss.tradeName != null ? ss.tradeName :tradeName}">
			</div>
		</div>

		<div class="form-group">
 			<label class="col-sm-2 control-label">備考 <span class="badge">部分一致</span></label>
 			<div class="col-sm-5">
				<input type="text" class="form-control" name="note" id="note" placeholder="備考" value="${ss.note != null ? ss.note :note}">
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-4">
				<button type="submit" class="btn btn-primary "><span class="glyphicon glyphicon-search"></span> 検 索</button>
				<a href="S0020.html" class="btn btn-default">クリア</a>
			</div>
		</div>

	</form>
	</div><!-- /container -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>