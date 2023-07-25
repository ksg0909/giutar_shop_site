<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String[] input = (String[])request.getAttribute("inputData");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>会員登録　確認画面</title>
		<link rel="stylesheet" href="css/common.css">
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<h1>会員登録　確認画面</h1>
		<p>下記の内容で登録します。よろしいですか？</p>
		<ul style="list-style: none;" class="ul">
			<li>ログインID：<%= input[0]%></li>
			<li>パスワード：<%= input[1]%></li>
			<li>名前　　　：<%= input[2]%></li>
			<li>生年月日　：<%= input[3]%></li>
			<li>都道府県　：<%= input[4]%></li>
			<li>詳細住所　：<%= input[5]%></li>
			<li>電話番号　：<%= input[6]%></li>
		</ul>
		<hr>
		<form action="InsertAccount" method="post">
			<input type="hidden" name="loginId" value="<%= input[0]%>">
			<input type="hidden" name="password" value="<%= input[1]%>">
			<input type="hidden" name="name" value="<%= input[2]%>">
			<input type="hidden" name="birthday" value="<%= input[3]%>">
			<input type="hidden" name="prefecture" value="<%= input[4]%>">
			<input type="hidden" name="address" value="<%= input[5]%>">
			<input type="hidden" name="tel" value="<%= input[6]%>">
			<input type="submit" value="会員登録する">
		</form>
		<div class="back">
			<a href="LinkServlet?link=createAnAccount">キャンセルする</a>
		</div>
	</body>
</html>