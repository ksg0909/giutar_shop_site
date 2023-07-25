<%-- login失敗 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%-- ログインエラー画面 --%>

<!DOCTYPE html>
<html>
    <head>
    	<meta charset="UTF-8">
        <title>エラー画面</title>
        <link rel="stylesheet" href="css/common.css">
    </head>
    <body>
    	<jsp:include page="header.jsp"></jsp:include>
        <main>
            <h1>ログインエラー</h1>
            <p>入力されたユーザは存在しません...</p>
            <div class="back">
                <a href="LinkServlet?link=login_main">戻る</a>
            </div>
        </main>
    </body>
</html>