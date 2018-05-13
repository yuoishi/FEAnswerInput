<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>リセット| 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<% request.setCharacterEncoding("UTF-8"); %>
	<div id="main">
		<h1>パスワード再設定</h1>
		<p>パスワードを再設定します</p>
		<p>パスワード再設定用のメールを送信するので以下の項目を入力してください</p>
		<form class="table1" action="/FEAnswerInput/Mail" method="post">
			<table>
				<tr>
					<th>ユーザID:</th>
					<td><input type="text" name="userid" pattern="^([a-zA-Z0-9]{6,16})$" required></td>
				</tr>
				<tr>
					<th>Eメールアドレス:</th>
					<td><input type="email" name="email" pattern="[\w.\-]+@[\w\-]+\.[\w.\-]+" required></td>
				</tr>
				<tr>
					<th>秘密の質問:</th>
					<td><input type="text" name="sq" maxlength="20" autocomplete="off" required></td>
				</tr>
			</table>
			<input type="hidden" name="hidden" value="0">
			<input type="submit" value="送信">
		</form>
		<a href="/FEAnswerInput/Login">ログイン画面に戻る</a>
	</div>
</body>
</html>