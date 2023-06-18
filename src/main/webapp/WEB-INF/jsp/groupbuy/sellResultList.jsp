<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>공동구매 판매 내역 조회</title>
	<style type = "text/css">
		body{margin:0}
		.layout{margin:0px auto;width:1180px;padding:10px;font-size:18px}
		.page{text-align:center;font-size:24px;margin-top:60px;margin-bottom:30px}
		th,td{border-top:1px solid gray;}
		
	</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
	<div class = "layout">
		<div class = "page">판매 내역 조회</div>
		<hr>
		<table>
			<tr>
				<th colspan = "2">상품명</th>
				<th>기능</th>
			</tr>
			<c:forEach items="${productList.getSource()}" var="prod">
				<tr>
					<td>
						<img src="<c:url value='${prod.picture1}'/>" width="250px" height="250px" />
					</td>
					<td>
						${prod.name}<br><br>
						${prod.color}<br>
						${prod.size}<br>
						${prod.description}
					</td>
					<td>
						<a href = "<c:url value='/groupbuy/detail.do'>
								<c:param name = 'productId' value='${prod.productId}' />
								</c:url>"><b style = "font-size:18px">상세보기</b>
						</a><br>
						<a href = "<c:url value='/groupbuy/update.do'>
								<c:param name = 'productId' value='${prod.productId}' />
								</c:url>"><b style = "font-size:18px">수정하기</b>
						</a><br>
						<a href = "<c:url value='/groupbuy/delete.do'>
								<c:param name = 'productId' value='${prod.productId}' />
								</c:url>"><b style = "font-size:18px">삭제하기</b>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>