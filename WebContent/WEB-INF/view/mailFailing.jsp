<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メール送信失敗| 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");

		String flg = (String)request.getAttribute("flg");
	%>
	<div id="main">
		<h1>メール送信失敗</h1>
		<% if(flg.equals("0")){ %>
			<p>メールの送信に失敗しました
		<% }else{ %>
			<p>登録内容と一致しません
		<% } %>
		<br>やり直してください</p><br>
		<form class="inlineblock" action="/FEAnswerInput/Top" method="get">
			<input type="submit" value="止める">
		</form>
		<form class="inlineblock" action="/FEAnswerInput/Reset" method="get">
			<input type="submit" value="もう一度">
		</form>
	</div>
</body>
</html>