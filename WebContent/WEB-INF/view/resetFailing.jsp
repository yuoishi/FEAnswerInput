<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>再設定失敗| 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<div id="main">
		<h1>パスワード再設定失敗</h1>
		<p class="p22">パスワード再設定に失敗しました<br>やり直してください</p><br>
		<form action="/FEAnswerInput/PwReset" method="get">
			<input type="submit" value="もう一度">
		</form>
	</div>
</body>
</html>