<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.User" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録内容確認| 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");

		User user= (User)session.getAttribute("user");
	%>
	<div id="main">
		<h1>登録内容</h1>
		<table>
			<tr>
				<th>ユーザID</th>
				<td><%= user.getUserID() %></td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td>セキュリティ上表示できません</td>
			</tr>
			<tr>
				<th>Eメールアドレス</th>
				<td><%= user.getEmail() %></td>
			</tr>
			<tr>
				<th>ユーザネーム</th>
				<td><%= user.getUserName() %></td>
			</tr>
			<tr>
				<th>名前</th>
				<td><%= user.getName() %></td>
			</tr>
			<tr>
				<th>フリガナ</th>
				<td><%= user.getPhoneticName() %></td>
			</tr>
			<tr>
				<th>誕生日</th>
				<td><%= user.getBirthday() %></td>
			</tr>
			<tr>
				<th>秘密の質問</th>
				<td><%= user.getSq() %></td>
			</tr>
		</table>
		<form class="inlineblock" action="/FEAnswerInput/RegistrationConfirmation" method="get">
			<input type="submit" value="修正">
		</form>
		<form class="inlineblock" action="/FEAnswerInput/CompletingRegistration" method="get">
			<input type="submit" value="登録">
		</form>
	</div>
</body>
</html>