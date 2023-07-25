<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ItemBean" %>
<!DOCTYPE html>

<% 
	ArrayList<ItemBean> cartList = (ArrayList<ItemBean>)session.getAttribute("CartList"); 
	int total = 0;
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>カート</title>
		<link rel="stylesheet" href="css/Cart.css">
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<h1>カート</h1>
		
			<% if(cartList != null){ %>
				<table class="item_table">
					<tr class="item_tr">
						<th>商品ID</th>
						<th>楽器部類</th>
						<th>メーカー</th>
						<th>モデル名</th>
						<th>シリアル</th>
						<th>値段</th>
						<th>個数</th>
						<th></th>
					</tr>
				
					<%-- 合計金額用整数型変数定義 --%>
					
					<% for(ItemBean b : cartList){ %>
					
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
							<%-- 個数&カートボタン --%>
							
							
							<td>
								<form action="CartServlet" method="get">
									<select name = "quantity"> 
										<% for(int i = 1; i <= b.getStock(); i++){ %>
										
										<option value = "<%= i %>" label = "<%= i %>">
										<%} %>
									</select>
									<input type="hidden" name= "item_id" value = <%= b.getItem_code()%>>
									<input type="hidden" name= "button" value = "delete">
									<input type="submit" value="カートからはずす">
								</form>
							</td>
						</tr>
						<br>
						 <% total += b.getPrice(); %>
					<% } %>
				</table>
				<div class="link">
					<a href="CartServlet?button=empty">カートを空にする</a><br>
				</div>
				<hr>
			<table class="total" action="PaymentServlet" method="post">
				<tr>
					<th>合計金額</th>
				</tr>
				<tr>
					<% 
						String format = "\\ %,7d";
						String totalF = String.format(format,total); 
					%>
					<td>
						<%=totalF %>
					</td>
				</tr>
				<tr>
					<td>
						<form action="PaymentServlet" method="post">
							<input type="hidden" name="total" value="<%= total %>">
							<input type="submit" value="ご購入">
						</form>
					</td>
				</tr>
			</table>
			<% }else{ %>
			
			<p>カートが空です...</p>
			<div class="back">
				<a href="./">戻る</a>
			</div>
			<% } %>
	</body>
</html>