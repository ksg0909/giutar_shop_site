<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ItemBean"%>
<!DOCTYPE html>

<jsp:useBean id="user_bean" scope="session" class="model.UserBean" />

<html>
	<head>
		<meta charset="UTF-8">
		<title>商品検索結果</title>
		<link href="css/search.css" rel="stylesheet"  type="text/css">
	</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main>
		<%
			List<ItemBean> beanlist = (List<ItemBean>) request.getAttribute("searchItem");
			String searchSnt =(String) request.getAttribute("searchSnt");
		%>
		<p>"<%= searchSnt %>"で検索した結果</p>
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
				
				
				<% for(ItemBean b : beanlist){ %>
				
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
						<td class="button">
							<form action="CartServlet" method="post">
								<select name = "quantity"> 
									<% for(int i = 1; i <= b.getStock(); i++){ %>
										
									<option value = "<%= i %>" label = "<%= i %>">
									<%} %>
								</select>
		     					<input type="hidden" name="item_id" value=<%= b.getItem_code()%>>
		      					<input type="submit" value="カートに入れる">
		      				</form>
		      			</td>
					</tr>
					
		      	
		      	<% } %>
			</table>
		</div>

	</main>
</body>
</html>