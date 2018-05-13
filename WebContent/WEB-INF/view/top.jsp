<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<div id="top">
		<h1>基本情報解答入力</h1>
		<form action="/FEAnswerInput/Login" method="get">
			<input type="submit" value="ログインへ">
		</form>
		<a href="/FEAnswerInput/UserRegistration">新規登録はこちらから</a>
	</div>
</body>
</html>