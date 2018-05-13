<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="dto.Score" %>
<%@ page import="dto.PreScore" %>
<%@ page import="dto.AmPm" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>採点結果| 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");

		Score score = (Score)session.getAttribute("score");
		PreScore preScore = (PreScore)session.getAttribute("preScore");
		String mon = (String)request.getAttribute("mon");
		String[] m = mon.split(",");

		AmPm ampm = (AmPm)session.getAttribute("ampm");
		int ampmID = ampm.getAmpmID();

		int scr = score.getScore();
		int preScr = preScore.getScore();

		String sOrF = score.getsOrF();
		String tOrF = score.gettOrF();

		String[] t = tOrF.split(",");
	%>
	<div id="main">
		<h1>スコア</h1>
		<p class="p22">あなたの得点</p>
		<div>
			<table class="scoa">
				<tr>
					<th class="p22">今回</th><th class="p22">前回</th>
				</tr>
				<tr>
					<td class="p22"><%= scr %>点</td><td class="p22"><%= preScr %>点</td>
				</tr>
			</table>
		</div>
		<p class="p22"><%= sOrF %></p>
		<table class="scoa">
		<tr>
			<% for(int i = 0; i < m.length / 2; i++){ %>
			<td><%= m[i] %><br><%= t[i] %></td>
			<% } %>
		</tr>
		<tr>
			<% for(int i = m.length / 2; i < m.length; i++){ %>
			<td><%= m[i] %><br><%= t[i] %></td>
			<% } %>
		</tr>
		</table>

		<form action="/FEAnswerInput/Exam" method="get" class="inlineblock">
			<input type="submit" value="終了">
		</form>

		<% if(ampmID == 0){ %>
			<form action="/FEAnswerInput/Answer" method="get" class="inlineblock">
				<input type="hidden" name="ampmid" value="1">
				<input type="submit" value="午後へ">
			</form>
		<% } %>
		<br>
		<a href="/FEAnswerInput/HOE" class="a">受験履歴</a><a href="/FEAnswerInput/Ranking" class="a">ランキング</a>
	</div>
</body>
</html>