<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>경매상품 리스트</title>
	<style type="text/css">
		table, td{border:1px solid black;border-collapse:collapse;}
	</style>
</head>
<body>
	<c:choose>
		<c:when test = "${productList.getSource() == null}">
			<div class = "sell">
				경매 상품이 하나도 없습니다.
			</div>
		</c:when>
		<c:otherwise>
			<c:set var="i" value="0" />
			<c:set var="j" value="4" />
			<table border="1">
			<c:forEach items="${productList.getSource()}" var="prod">
				<c:if test="${i%j == 0 }">
			    	<tr>
			    </c:if>
			    	<td>
			    		<a href = "<c:url value='/auction/detail.do'>
							<c:param name = 'productId' value='${prod.productId}' />
							</c:url>">
				   			<img src="<c:url value='${prod.picture1}'/>" width="250px" height="250px"/><br>
				    	  		<b>${prod.name}</b><br>
				      			${prod.description}<br>
				      			color: ${prod.color} || size: ${prod.size}<br><br>
<%-- 				      			${prod.maxPrice}원<br> 이건 prod 에서 받아오는게 아니라 따로 쿼리를 써야할거 같아요!--%>
				      			현재 최고가 : 구하는 쿼리 필요
				   		</a>
			      	</td>
				<c:if test="${i%j == j-1 }">
			    	</tr>
			    </c:if>
			    <c:set var="i" value="${i+1 }" />
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>