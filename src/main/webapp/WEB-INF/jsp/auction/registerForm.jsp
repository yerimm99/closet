<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>경매 등록폼</title>
	<style type = "text/css">
		body{margin:0}
		.layout{margin:0px auto;width:1180px;padding:10px}
		table, td{border:2px solid black;border-collapse:collapse;}
	</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
	<div class = "layout">
		<form:form modelAttribute = "auctionForm" method="post">
		<table>
			<tr>
				<td>
					<form:input path = "auction.name" placeholder = "상품명"/>
				</td>
			</tr>
			<tr>
				<td>
					<form:input path = "auction.size" placeholder = "사이즈"/>
				</td>
			</tr>
			<tr>
				<td>
					<form:input path = "auction.color" placeholder = "색상"/>
				</td>
			</tr>
			<tr>
				<td>
					<form:input path = "auction.description" placeholder = "상품 설명"/>
				</td>
			</tr>
<%-- 			<tr>
				<td>
					<form:radiobuttons path = "auction.type" name = "type" value = "0"/>
				</td>
			</tr> --%>
			<tr>
				<td>
					<form:input path = "auction.startPrice" placeholder = "최소가격"/>
				</td>
			</tr>
			
			<tr>
				<td>
					
				</td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>