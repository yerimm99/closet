<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>공동구매 상품리스트</title>
<style type="text/css">
table, td {border: 1px solid black;border-collapse: collapse;}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${productList.getSource() == null}">
			<div class="sell">공동구매 상품이 하나도 없습니다.</div>
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
						<a href="<c:url value='/groupbuy/detail'>
 							<c:param name = 'productId' value='${prod.productId}' />
 							</c:url>">
							<img src="<c:url value='${prod.picture1}'/>" width="250px"
							height="250px" /><br> ${prod.name}<br>
							${prod.description}<br> color: ${prod.color} || size:${prod.size}<br><br>
							가격: ${prod.price}원<br> 현재모인 인원수:${prod.peopleNum} <!-- 이거 전체인원수랑 현재 인원수로 나눠야함.. -->
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