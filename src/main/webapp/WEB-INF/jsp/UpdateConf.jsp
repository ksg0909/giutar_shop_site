<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="model.UserBean" %>
<!DOCTYPE html>
<%
	UserBean user = (UserBean)request.getAttribute("Bean");
	session.setAttribute("provBean", user);
%>
<html>
<head>
		<meta charset="UTF-8">
		<title>お客様情報変更</title>
		<link rel="stylesheet" href="css/common.css">
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<h1>お客様情報変更</h1>
		<p>下記の内容に変更します。問題ございませんでしたら確定ボタンを押してください。</p>
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
			
			<input type="hidden" name="state" value="conf">
			<input type="submit" value="変更確定">
		</form>
		<form action="UpdateAccount" method="post">
			<input type="hidden" name="state" value="before">
			<input type="submit" value="編集画面に戻る">
		</form>
	</body>
</html>