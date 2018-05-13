<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ登録 | 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String d = sdf.format(cal.getTime());
		int date = Integer.parseInt(d);
	%>
	<div id="main">
		<h1>新規登録</h1>
		<p><span style="color: red">＊</span>全て入力必須です</p>
		<form action="/FEAnswerInput/RegistrationConfirmation" method="post">
			<table>
				<tr>
					<th>ユーザID</th>
					<td><input type="text" name="userid" pattern="^([a-zA-Z0-9]{6,16})$" required></td>
					<td>半角英数字6～16文字</td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[._])[a-zA-Z0-9._]{8,16}" required></td>
					<td>大小英字数字と記号(.か_)を含む8～16文字</td>
				</tr>
				<tr>
					<th>Eメールアドレス</th>
					<td><input type="email" name="email" pattern="[\w.\-]+@[\w\-]+\.[\w.\-]+" required></td>
					<td></td>
				</tr>
				<tr>
					<th>ユーザネーム</th>
					<td><input type="text" name="username" maxlength="8" required></td>
					<td>8文字以内</td>
				</tr>
				<tr>
					<th>氏名</th>
					<td><input type="text" name="name" maxlength="15" required></td>
					<td>15文字以内</td>
				</tr>
				<tr>
					<th>フリガナ</th>
					<td><input type="text" name="phoneticname" pattern="^([\u30A1-\u30FAー]{1,30})$" required></td>
					<td>カタカナのみ30文字以内</td>
				</tr>
				<tr>
					<th>誕生日</th>
					<td><select name="年" required>
						<% for(int i = date; i >= 1945; i--){ %>
							<option><%= i %></option>
						<% } %>
					</select>年&nbsp;
					<select name="月" required>
						<% for(int i = 1; i <= 12; i++){ %>
							<option><%= i %></option>
						<% } %>
					</select>月&nbsp;
					<select name="日" required>
						<% for(int i = 1; i <= 31; i++){ %>
							<option><%= i %></option>
						<% } %>
					</select>日</td>
					<td></td>
				</tr>
				<tr>
					<th>秘密の質問</th>
					<td><input type="text" name="sq" maxlength="20" autocomplete="off" required></td>
					<td>パスワード再設定時のみ使用　20文字以内</td>
				</tr>
			</table>
			<input type="submit" value="確認">
		</form>
		<a href="/FEAnswerInput/Login">ログイン画面に戻る</a>
	</div>
</body>
</html>