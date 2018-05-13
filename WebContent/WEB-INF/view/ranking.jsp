<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ランキング| 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String str = (String)request.getAttribute("str");
	%>
	<div id="main">
		<h1>基本情報技術者試験得点ランキング</h1>
		<a href="#am" class="a">午前</a><a href="#pm" class="a">午後</a>
		<div id="ranking">
		<%= str %>
		</div>
		<form action="/FEAnswerInput/Exam" method="get">
			<input type="submit" value="終了">
		</form>
	</div>
</body>
</html>