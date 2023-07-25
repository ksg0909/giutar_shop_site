<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<title>マイページ</title>
		<link rel="stylesheet" href="css/myPage.css">
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<main>
			<div class="main_content">
				<h1>マイページ</h1><br>
				<div class="link">
					<a href="LinkServlet?link=myPageAccountInfo">お客様情報</a><br>
					<a href="HistoryServlet">ご購入履歴</a><br>
					<a href="Login_Servlet?submit=ログアウト">ログアウト</a><br>
				</div>
				<hr>
				<div class="back">
					<a href="./">戻る</a>
				</div>
			</div>
		</main>
	</body>
</html>