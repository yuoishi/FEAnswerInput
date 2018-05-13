<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="dto.Exam" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>問題年度別 | 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		Exam exam = (Exam)session.getAttribute("exam");
	%>
	<div id="main">
		<h1><%= exam.getExamName() %></h1>
		<a href="/FEAnswerInput/Answer?ampmid=0" class="a">午前</a>
		<a href="/FEAnswerInput/Answer?ampmid=1" class="a">午後</a>
		<a href="/FEAnswerInput/Exam" class="a">問題選択画面に戻る</a>
	</div>
</body>
</html>