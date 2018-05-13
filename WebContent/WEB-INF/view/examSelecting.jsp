<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>問題選択 | 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%
		String all = (String)request.getAttribute("all");
		String am = (String)request.getAttribute("am");
		String pm = (String)request.getAttribute("pm");
	%>
	<div id="main">
		<h1>基本情報技術者試験</h1>
		<a href="#am" class="a">午前</a><a href="#pm" class="a">午後</a>
		<table class="es">
			<tr>
				<th></th>
				<th>年度別</th>
				<th>合格率</th>
			</tr>
			<%= all %>
		</table>
		<table class="es">
			<tr id="am">
				<th></th>
				<th>午前</th>
				<th>合格率</th>
			</tr>
			<%= am %>
		</table>
		<table class="es">
			<tr id="pm">
				<th></th>
				<th>午後</th>
				<th>合格率</th>
			</tr>
			<%= pm %>
		</table>
		<a href="/FEAnswerInput/Top" class="a"><% session.setAttribute("logout", "logout");%>ログアウト</a><a href="/FEAnswerInput/HOE" class="a">受験履歴表示</a><a href="/FEAnswerInput/Ranking" class="a">ランキング表示</a>
	</div>
</body>
</html>