<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>로그인폼</title>
	<style type="text/css">
		.layout{margin:0px auto;width:1180px;padding:10px;font-size:18px}
		table, td{border:none;}
		.inp{width:500px;height:35px}
		table{margin:150px auto 0px auto}
		td{height:40px}
		.btn{text-align:center;font-size:20px;border-radius:10px;background-color:black;
		border:1px solid black;width:510px;height:35px;color:white;margin-top:10px}
	</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
	<div class = "layout">
		<!-- 로그인 폼 -->
		<form name="form" method="POST" action="<c:url value='/main.do' />">
			<table>
				<tr>
					<td style="text-align:center;font-size:24px">로그인</td>
				</tr>
				<tr>
					<td>
						<br><br>
						<input type="text" name="userId" placeholder = "아이디 입력" size = "40"
							class = "inp">
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" name="password" placeholder = "비밀번호 입력"
							class = "inp">
					</td>
				</tr>
				<tr>
					<td>
						<input type = "submit" class = "btn" value = "로그인">
					</td>
				</tr>
			</table><br>
			<!-- <div class = "btnBox">
				<input type="submit" value="로그인" onClick="login()" class = "btn"> &nbsp;
			</div>-->
		</form>
	</div>
</body>
</html>