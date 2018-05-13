<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メール送信完了| 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");

		String remove = (String)request.getAttribute("remove");
		User user = (User)request.getAttribute("user");
	%>
	<div id="main">
		<h1>メール送信完了</h1>
		<p>メールを送信しました<br>メールに記載されているパスコードを入力して<br>
		パスワードの再設定を行ってください</p>
		<form class="table1" action="/FEAnswerInput/Mail" method="post">
			<%= remove %>
			<table>
				<tr>
					<th>パスコード:</th>
					<td><input type="text" name="passcode" pattern="[0-9]{7}" required></td>
				</tr>
			</table>
			<input type="hidden" name="hidden" value="2">
			<input type="submit" value="送信">
		</form>
		<form action="/FEAnswerInput/Mail" method="post">
			<input type="hidden" name="hidden" value="1">
			<input type="submit" value="メールの再送">
		</form>
	</div>
</body>
</html>