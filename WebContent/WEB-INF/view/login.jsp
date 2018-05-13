<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン | 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String flg = (String)request.getAttribute("flg");
	%>
	<div id="main">
		<h1>ログイン</h1>
		<p>ユーザIDとパスワードを入力してログインしてください</p>
		<% if(flg != null){ %>
			<label class="alert">ユーザIDかパスワードが違います</label>
		<% }else{ %>
			<br>
		<% } %>
		<form class="table1" action="/FEAnswerInput/Login" method="post">
			<table>
				<tr>
					<th>ユーザID:</th>
					<td><input type="text" name="userid" pattern="^([a-zA-z0-9]{6,16}$)" required></td>
				</tr>
				<tr>
					<th>パスワード:</th>
					<td><input type="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]){8,16}" required></td>
				</tr>
			</table>
			<input type="submit" value="ログイン"><br>
			<a href="/FEAnswerInput/UserRegistration">新規登録の方はこちらから</a><br>
			<a href="/FEAnswerInput/Reset">パスワードを忘れた方はこちらから</a>
		</form>
	</div>
</body>
</html>