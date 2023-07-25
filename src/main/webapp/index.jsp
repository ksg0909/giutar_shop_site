<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="user_bean" scope="session" class="model.UserBean" />

<html>
	<head>
		<meta charset="UTF-8">
		<title>KSG楽器</title>
		<meta name="description" 
			  content="KSG楽器はギター、ベースを取り扱うオンラインショップです。
			  人気商品を多数取り揃えております。">
		<link rel="stylesheet" href="css/style_top.css">
	</head>
	<body>
		<header class="header">
			<div class ="header_top_bar">
				
				<nav class="header_top_nav">
					<div class = "header_logo">
						<a href="index.jsp"><img class = "title_logo" src="./img/KSG_logo.png"></a>
					</div>
					
					
					<ul class="header_top_nav_list">
						<li class="top_nav_litag"><a href="LinkServlet?link=guide"><img src="./img/info_before.svg" class="info_icon">ご利用ガイド</a></li>
						<li class="top_nav_litag"><a href="CartServlet?button=show"><img src="./img/cart_before.svg" class="cart_icon">カート</a></li>
						<% if ("login".equals(session.getAttribute("login_state"))) { %>
						<li class="top_nav_litag"><a href="LinkServlet?link=myPage"><img src="./img/login_before.svg" class="login_icon">マイページ</a></li>
						<% }else{ %>
						<li class="top_nav_litag"><a href="LinkServlet?link=login_main"><img src="./img/login_before.svg" class="login_icon">ログイン</a></li>
						<% }  %>
					</ul>
				</nav>
			</div>
			<div class = "header_bottom_bar">	
				
				<nav class="header_bottom_nav">
					<ul class="header_bottom_nav_list">
						<li class = "header_bottom_nav_litag"><a href="SearchServlet?search=E.Guitar">E.Guitar</a></li>
						<li class = "header_bottom_nav_litag"><a href="SearchServlet?search=A.Guitar">A.Guitar</a></li>
						<li class = "header_bottom_nav_litag"><a href="SearchServlet?search=Bass">Bass</a></li>
						<li class = "header_bottom_nav_litag"><a href="SearchServlet?search=Amp">Amp</a></li>
						<li class = "header_bottom_nav_litag"><a href="SearchServlet?search=Effector">Effector</a></li>
					</ul>
					<div class = "search_form_div">
						<form class = "search_form" action="SearchServlet" method="get">
							<input class ="search" type="search" name="search" placeholder="キーワードを入力">
							<input class ="submit"type="submit" value="検索">
						</form>
					</div>
				</nav>
			</div>
			<div class="top_photo_content">
				<img class="top_photo" src="./img/top_photo.png">
			</div>
			
		</header>
	</body>
</html>