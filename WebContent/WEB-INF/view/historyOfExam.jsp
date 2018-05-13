<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>受験履歴 | 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String str = (String)request.getAttribute("str");
	%>
	<div id="main">
		<h1>受験履歴</h1>
		<table class="hoe">
			<tr>
				<th>日時</th><th>問題年度</th><th>午前/午後</th><th>得点</th><th>合否</th>
			</tr>
			<%= str %>
		</table>
		<form class="sort" action="/FEAnswerInput/HOE" method="get">
			<table>
				<caption>ソート方法</caption>
				<tr>
					<td></td>
					<td colspan="2">年度</td>
					<td colspan="2">得点</td>
					<td colspan="2">午前/午後</td>
				</tr>
				<tr>
					<td><input type="radio" name="date" value="64">日時</td>
					<td><input type="radio" name="year" value="32">降順</td>
					<td><input type="radio" name="year" value="16">昇順</td>
					<td><input type="radio" name="score" value="8">降順</td>
					<td><input type="radio" name="score" value="4">昇順</td>
					<td><input type="radio" name="ampm" value="2">午前</td>
					<td><input type="radio" name="ampm" value="1">午後</td>
				</tr>
			</table>
			<input type="hidden" name="sort" value="1">
			<input type="submit" value="ソート">
		</form>
		<form action="/FEAnswerInput/Exam" method="get">
			<input type="submit" value="終了">
		</form>
	</div>
</body>
</html>