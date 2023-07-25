<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<title>ログイン</title>
		<link rel="stylesheet" href="css/login_main.css">
	</head>
	<body >
		<jsp:include page="header.jsp"></jsp:include>
		<h1>ログイン画面</h1>
		<div class="form">
			<form action="Login_Servlet" method="post">
				ログインID:<input type="text" name="id"size="20"><br><br>
				パスワード:<input type="password" name="password" size="20"><br><br>
				<input type="submit" name="submit" value="ログイン">
				
			</form>
		</div>
		<br>
		<div class="link">
		アカウントをお持ちでない場合は<a href="LinkServlet?link=createAnAccount">登録</a>
		</div>
		<br><br>
		<div class="link">
			<a href="./">戻る</a>
		</div>
	</body>
</html>