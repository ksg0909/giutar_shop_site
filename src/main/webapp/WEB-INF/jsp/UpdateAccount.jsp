<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "model.UserBean" %>
<!DOCTYPE html>
<%
	UserBean user = (UserBean)session.getAttribute("user_bean");
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>お客様情報変更</title>
	<link rel="stylesheet" href="css/common.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main>
		<h1>お客様情報変更</h1>
		<form action="UpdateAccount" method="post">
			<table>
				<tr>
					<th>ログインID</th>
					<td><input type="text" name="loginId" value="<%=user.getLoginId()%>"></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="text" name="password" value="<%=user.getPassword()%>"></td>
				</tr>
				<tr>
					<th>お名前</th>
					<td><input type="text" name="name" value="<%=user.getName()%>"></td>
				</tr>
				<tr>
					<th>生年月日</th>
					<td><input type="date" name="birthday" value="<%=user.getStrBirthday()%>"></td>
				</tr>
				<tr>
					<th>都道府県</th>
					<td><select name="prefecture">
							<option value="" selected>都道府県</option>
							<option value="北海道">北海道</option>
							<option value="青森県">青森県</option>
							<option value="岩手県">岩手県</option>
							<option value="宮城県">宮城県</option>
							<option value="秋田県">秋田県</option>
							<option value="山形県">山形県</option>
							<option value="福島県">福島県</option>
							<option value="茨城県">茨城県</option>
							<option value="栃木県">栃木県</option>
							<option value="群馬県">群馬県</option>
							<option value="埼玉県">埼玉県</option>
							<option value="千葉県">千葉県</option>
							<option value="東京都">東京都</option>
							<option value="神奈川県">神奈川県</option>
							<option value="新潟県">新潟県</option>
							<option value="富山県">富山県</option>
							<option value="石川県">石川県</option>
							<option value="福井県">福井県</option>
							<option value="山梨県">山梨県</option>
							<option value="長野県">長野県</option>
							<option value="岐阜県">岐阜県</option>
							<option value="静岡県">静岡県</option>
							<option value="愛知県">愛知県</option>
							<option value="三重県">三重県</option>
							<option value="滋賀県">滋賀県</option>
							<option value="京都府">京都府</option>
							<option value="大阪府">大阪府</option>
							<option value="兵庫県">兵庫県</option>
							<option value="奈良県">奈良県</option>
							<option value="和歌山県">和歌山県</option>
							<option value="鳥取県">鳥取県</option>
							<option value="島根県">島根県</option>
							<option value="岡山県">岡山県</option>
							<option value="広島県">広島県</option>
							<option value="山口県">山口県</option>
							<option value="徳島県">徳島県</option>
							<option value="香川県">香川県</option>
							<option value="愛媛県">愛媛県</option>
							<option value="高知県">高知県</option>
							<option value="福岡県">福岡県</option>
							<option value="佐賀県">佐賀県</option>
							<option value="長崎県">長崎県</option>
							<option value="熊本県">熊本県</option>
							<option value="大分県">大分県</option>
							<option value="宮崎県">宮崎県</option>
							<option value="鹿児島県">鹿児島県</option>
							<option value="沖縄県">沖縄県</option>
					</select></td>
				</tr>
				<tr>
					<th>詳細住所</th>
					<td><input type="text" name="address" value="<%=user.getAddress()%>"></td>
				</tr>
				<tr>
					<th>電話番号</th>
					<td><input type="text" name="tel" value="<%=user.getTel()%>"></td>
				</tr>
			</table>
			<input type="hidden" name= "state" value="after">
			<input type="submit" value="変更">
			<input type="reset" value="クリア">
		</form>
	</main>
</body>
</html>