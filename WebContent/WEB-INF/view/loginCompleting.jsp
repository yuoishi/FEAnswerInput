<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン成功 | 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script>
	window.location.href = "/FEAnswerInput/Exam";
</script>
</head>
<body>
	<div id="main">
		<h1>ログイン成功</h1>
		<noscript>
			<p class="p22">ログインに成功しました</p>
			<p class="p22"><a href="/FEAnswerInput/Exam">こちら</a>から問題選択画面に進んでください</p>
		</noscript>
	</div>
</body>
</html>