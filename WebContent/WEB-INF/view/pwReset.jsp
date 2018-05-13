<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード再設定| 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");

		String flg = (String)request.getAttribute("flg");
	%>
	<div id="main">
		<h1>パスワード再設定</h1>
		<% if(flg != null){ %>
			<label>入力内容が一致しません</label>
		<% }else{ %>
			<br>
		<% } %>
		<form class="inlineblock" action="/FEAnswerInput/PwReset" method="post">
			<table>
				<tr>
					<th>新パスワード</th>
					<td><input type="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}" required></td>
					<td>大小英字数字と記号(.か_)を含む8～16文字</td>
				</tr>
				<tr>
					<th>パスワード再入力</th>
					<td><input type="password" name="pw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}" required></td>
					<td></td>
				</tr>
			</table>
			<input type="submit" value="登録">
		</form>
	</div>
</body>
</html>