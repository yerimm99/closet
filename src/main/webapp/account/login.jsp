<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>로그인폼</title>
	<style type="text/css">
		.layout{margin:0px auto;width:1180px;padding:10px;font-size:18px}
		table, td{border:none;}
		input{width:500px;height:30px}
		table{margin:250px auto 0px auto}
		td{height:40px}
		.btn{text-align:center;font-size:18px;border-radius:10px;background-color:gray;
		border:1px solid black;width:100px;height:30px}
		.btnBox{width:350px;height:30px;margin:auto;background-color:white;text-align:center}
	</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
	<div class = "layout">
		<!-- 로그인 폼 -->
		<form name="form" method="POST" action="<c:url value='/account/login' />">
			<table>
				<tr>
					<td>로그인</td>
				</tr>
				<tr>
					<td>
						<input type="text" style="width:230px" name="userId" placeholder = "아이디 입력" size = "40">
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" style="width:230px" name="password" placeholder = "비밀번호 입력">
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