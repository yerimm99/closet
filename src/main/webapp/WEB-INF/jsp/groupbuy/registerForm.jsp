<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>공동구매 상품 등록폼</title>
	<style type = "text/css">
		body{margin:0}
		.layout{margin:0px auto;width:1180px;padding:10px;font-size:18px}
		table, td{border:none;border-collapse:collapse;}
		.inp{width:500px;height:35px}
		.inpText{width:500px;height:80px}
		table{margin:50px auto 0px auto}
		td{height:40px}
		input{border:none;}
	</style>
</head>
<body>
<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
	<div class = "layout">
		<form:form modelAttribute = "groupbuyForm" method="POST" action="<c:url value='/groupbuy/registerForm' />">
		<table>
			<tr>
				<td style="text-align:center;font-size:24px">공동구매 상품 등록</td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>