<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.ItemBean" %>
<%@ page import="model.UserBean" %>
<%@ page import="model.HistoryBean" %> 
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>   
<!DOCTYPE html>
<%
	
	UserBean user = (UserBean)session.getAttribute("user_bean");
	ArrayList<HistoryBean> his = (ArrayList<HistoryBean>)session.getAttribute("History");
	ArrayList<ItemBean> itm = (ArrayList<ItemBean>)session.getAttribute("HisItem");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>マイページ</title>
		<link rel="stylesheet" href="css/History.css">
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<h1>ご購入履歴</h1>
		<p><%=user.getName() %> 様のご購入履歴<p>
		
		<% if(his.isEmpty()){ %>
		<p>過去ご購入された履歴がございません。</p>
		<% }else{ %>
		<table>
			<tr>
				<th>楽器分類</th>
				<th>メーカー</th>
				<th>商品名</th>
				<th>お値段</th>
				<th>ご購入日</th>
				<th>個数</th>
			</tr>
			
			<%  
			for(HistoryBean hb : his){ 
				String itemCode= hb.getItemCode(); 
				
				for(ItemBean ib : itm){
					
					if(ib.getItem_code().equals(itemCode)){
			%>
					<tr>
						<td><%=ib.getType() %></td>
						<td><%=ib.getMaker() %></td>
						<td><%=ib.getName() %></td>
						<td><%=ib.getPrice() %></td>
						<td><%=hb.getStrBuyDate() %></td>
						<td><%=hb.getQuantity() %></td>
					</tr>
					<%} %>
				<% } %>
			<% } %>
		</table>
		<% } %>
		<div class="back">
			<a href = "LinkServlet?link=myPage">マイページへ戻る</a>
		</div>
	</body>
</html>