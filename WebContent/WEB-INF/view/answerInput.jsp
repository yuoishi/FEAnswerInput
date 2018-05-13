<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="dto.AmPm" %>
<%@ page import="dto.Exam" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>解答入力 | 基本情報解答入力</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script>
	/*動的に解答欄を表示させる処理*/

	function checkSentaku( num ){
		document.getElementById("toi" + num).style.display = "block";
		for(var i = 0; i < document.form1.sentaku.length; i++){
			if(!document.form1.sentaku[i].checked){
				document.getElementById('toi' + (i + 2)).style.display = "none";
			}
		}
	}

	function checkGengo( num ){
		document.getElementById("toi" + num).style.display = "block";
		for(var i = 0; i < document.form1.gengo.length; i++){
			if(!document.form1.gengo[i].checked){
				document.getElementById("toi" + (i + 9)).style.display = "none";
			}
		}
	}
</script>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		AmPm ampm = (AmPm)session.getAttribute("ampm");
		Exam exam = (Exam)session.getAttribute("exam");
		String str = (String)request.getAttribute("str");
	%>
	<div id="main">
		<h1><%= exam.getExamName() %><%= ampm.getAmpmName() %></h1>
		<form name="form1" action="/FEAnswerInput/Score" method="post">
			<%= str %>
			<input type="submit" value="採点">
		</form>
	</div>
</body>
</html>