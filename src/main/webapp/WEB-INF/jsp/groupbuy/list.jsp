<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>�������� ��ǰ����Ʈ</title>
	<style type="text/css">
		table, td{border:2px solid black;border-collapse:collapse;}
	</style>
</head>
<body>
	<c:set var="i" value="0" />
	<c:set var="j" value="4" />
	<table border="1">
	<c:forEach items="${productList}" var="prod">
		<c:if test="${i%j == 0 }">
	    	<tr>
	    </c:if>
	    	<td>
	    		<a href = "<c:url value='/groupbuy/detail'>
									<c:param name = 'productId' value='${prod.productId}' />
									</c:url>">
		      		<img src="<c:url value='${prod.picture1}'/>" width="250px" height="250px"/><br>
		      		${prod.name}<br>
		      		${prod.description}<br>
		      		color: ${prod.color} || size: ${prod.size}<br><br><br>
		      		����: ${prod.maxPrice}��<br>
		      		������� �ο���: ${prod.peopleNum} <!-- �̰� ��ü�ο����� ���� �ο����� ��������.. -->
	      		</a>
	      	</td>
		<c:if test="${i%j == j-1 }">
	    	</tr>
	    </c:if>
	    <c:set var="i" value="${i+1 }" />
	</c:forEach>
	</table>
</body>
</html>