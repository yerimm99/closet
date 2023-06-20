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
		.text-center{margin-top:60px;text-align:center;}
		.text-center a{margin:0px 20px; display:inline-block}
		.row{width:100%;}
	</style>
</head>
<body>
		<c:choose>
			<c:when test="${productList.getSource() == null}">
				<div class="sell">공동구매 상품이 하나도 없습니다.</div>
			</c:when>
			<c:otherwise>
				<table border="1">
					<c:forEach var="prod" items="${productList.pageList}">
					<tr bgcolor="#FFFF88"><td>
						<a href="<c:url value='/groupbuy/detail.do'>
 							<c:param name = 'productId' value='${prod.productId}' />
 							</c:url>">
								<img src = "../../upload/${prod.picture1}">/><br>
							<b> ${prod.name}</b><br> ${prod.description}<br> color:
								${prod.color} || size:${prod.size}<br>
							<br> 가격: ${prod.price}원<br> 현재모인 인원수: ${prod.peopleSum} / ${prod.peopleNum}
						</a>
						</td>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
		<!-- 이전 페이지, 다음 페이지 버튼 -->
    <form action="/closet/groupbuy2.do?pageName=previous" method="get">
        <input type="hidden" name="pageName" value="previous">
        <input type="submit" value="Previous">
    </form>

    <form action="/closet/groupbuy2.do?pageName=next" method="get">
        <input type="hidden" name="pageName" value="next">
        <input type="submit" value="Next">
    </form>
</body>
</html>