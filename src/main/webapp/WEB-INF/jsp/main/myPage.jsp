<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>마이페이지</title>
	<style type = "text/css">
		body{margin:0}
		.layout{margin:0px auto;width:1180px;padding:10px}
		.page{text-align:center;font-size:24px}
		.accountInfo{border:1px solid gray;}
		a{display:block}
	 	a:link{text-decoration:none;color:black}
		a:visited{text-decoration:none;color:black}
		a:active{text-decoration:none;color:black}
		a:hover{text-decoration:none;color:black}
	</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
	<div class = "layout">
		<div class = "page">마이페이지</div>
		<div class = "accountInfo">
			<table>
				<tr>
					<td colspan = "3">${account.username}님</td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td>${account.phone}</td>
					<td rowspan = "3">
						<a href = "<c:url value='/account/updateForm.do'>
							<c:param name = 'userId' value='${account.userId}' />
							</c:url>">회원정보 수정
						</a>
					</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>${account.email}</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>${account.address}</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>