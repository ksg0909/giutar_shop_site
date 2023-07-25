<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.List" %>
<%@ page import = "model.UserBean" %>
<!DOCTYPE html>
<%
	UserBean user = (UserBean)session.getAttribute("user_bean");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>マイページ</title>
		<link rel="stylesheet" href="css/AccountInfo.css">
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<h1>お客様情報</h1>
		<div class="table">
			<table>
				<tr><th>ログインID</th><td><%=user.getLoginId() %></td></tr>
				<tr><th>パスワード</th><td><%=user.getPassword() %></td></tr>
				<tr><th>お名前</th><td><%=user.getName() %></td></tr>
				<tr><th>生年月日</th><td><%=user.getStrBirthday() %></td></tr>
				<tr><th>都道府県</th><td><%=user.getPrefecture() %></td></tr>
				<tr><th>詳細住所</th><td><%=user.getAddress() %></td></tr>
				<tr><th>電話番号</th><td><%=user.getTel() %></td></tr>
			</table>
			<form action="UpdateAccount" method="post">
				<input type= "hidden" name= "state" value= "before">
				<input type= "submit" value= "お客様情報の変更はこちらから">
			</form>
		</div>
		<br>
		<div class="back">
			<a href="LinkServlet?link=myPage">マイページへ戻る</a>
		</div>
	</body>
</html>