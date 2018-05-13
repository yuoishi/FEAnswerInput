<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="dto.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録完了| 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		User user = (User)session.getAttribute("user");
	%>
	<div id="main">
		<h1>登録完了</h1>
		<p class="p22"><%= user.getUserID() %>さん</p>
		<p class="p22">登録完了しました</p>
		<p class="p22">サービスをご利用頂くにはログインをしてください</p><br>
		<form action="/FEAnswerInput/Login" method="get">
			<input type="submit" value="ログイン">
		</form>
	</div>
</body>
</html>