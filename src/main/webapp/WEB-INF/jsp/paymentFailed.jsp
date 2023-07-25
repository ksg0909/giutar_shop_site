<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ItemBean" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ご購入手続き</title>
		<link rel="stylesheet" href="css/common.css">
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<h1>ご購入手続き</h1>
		<% String state = (String)request.getAttribute("payment_state"); %>
		
		<% if(state.equals("logout")){ %>
		<p>ユーザー情報が現在設定されておりません。</p>
		<p>ログイン、または会員登録をしてください。</p>
		
		<form action="LinkServlet" method="get">
			<input type="hidden" name="link" value="login_main">
			<input type="submit" value="ログイン">
		</form>
		<br>
		<form action="LinkServlet" method="get">
			<input type="hidden" name="link" value="createAnAccount">
			<input type="submit" value="会員登録">
		</form>
		<% }else if(state.equals("shortage")){ %>
		<p>誠に申し訳ございません。</p>
		<p>下記のお品物は在庫不足により指定個数でご購入いただけません。</p>
		<%
			ArrayList<ItemBean> list = (ArrayList<ItemBean>)request.getAttribute("shortage");
		%>
		<div class="search_result">

			<table class="item_table">
				<tr class="item_tr">
					<th>商品ID</th>
					<th>楽器部類</th>
					<th>メーカー</th>
					<th>モデル名</th>
					<th>シリアル</th>
					<th>値段</th>
					<th></th>
				</tr>
				
				
				<% for(ItemBean b : list){ %>
				
					<tr class="item_tr">
						<%-- 商品コード --%>
						<td><%= b.getItem_code() %></td>
						<%-- 楽器分類 --%>
						<td><%= b.getType() %></td>
						<%-- メーカー --%>
						<td><%= b.getMaker() %></td>
						<%-- モデル名 --%>
						<td><%= b.getName() %></td>
						<%-- シリアルナンバー --%>
						<td><%= b.getSerial() %></td>
						
						<%-- 値段用フォーマット --%>
						<%
							String format = "\\ %,7d";
							String price = String.format(format, b.getPrice());
						%>
						<%-- 値段 --%>
						<td><%= price %></td>
					</tr>
					
		      	
		      	<% } %>
			</table>
		</div>
		<a href="CartServlet">カートへ戻る</a>
		<% } %>
	</body>
</html>