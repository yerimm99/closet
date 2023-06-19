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
	<c:set value="${paging}" var="paging"></c:set>
	<div class="row">
		<c:choose>
			<c:when test="${productList == null}">
				<div class="sell">공동구매 상품이 하나도 없습니다.</div>
			</c:when>
			<c:otherwise>
				<c:set var="i" value="0" />
				<c:set var="j" value="4" />
				<table border="1">
					<c:forEach items="${productList}" var="prod">
						<c:if test="${i%j == 0 }">
							<tr>
						</c:if>
						<td><a
							href="<c:url value='/groupbuy/detail.do'>
 							<c:param name = 'productId' value='${prod.productId}' />
 							</c:url>">
								<img src="<c:url value='${prod.picture1}'/>" width="250px"
								height="250px" /><br>
							<b> ${prod.name}</b><br> ${prod.description}<br> color:
								${prod.color} || size:${prod.size}<br>
							<br> 가격: ${prod.price}원<br> 현재모인 인원수: ${prod.peopleSum} / ${prod.peopleNum}
						</a></td>
						<c:if test="${i%j == j-1 }">
							</tr>
						</c:if>
						<c:set var="i" value="${i+1 }" />
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
		<!-- 페이징 처리 -->
		<div class="text-center">
			<!-- 이전 페이지 그룹 있나? -->
			<c:if test="${paging.hasPrevious==true }">
				<a href="?page=${preview}&categoryId=${categoryId}">
					<i class="fas fa-lg fa-angle-double-left"></i>
				</a>
				&nbsp;
			</c:if>
			<!-- 페이지 번호 표시 -->
			<c:forEach var="pagingNumber" begin="${paging.nowPageGroupStartPage }" end="${paging.nowPageGroupEndPage }">
				<c:choose>
					<c:when test="${pagingNumber==paging.nowPage }">
						<i class="fas fa-lg fa-sort-numeric-up-alt">
							<a href="?page=${pagingNumber-1 }&categoryId=${categoryId}"
								class="board-number-change-color abncc">${pagingNumber}
							</a>
						</i>
							&nbsp;
						</c:when>
					<c:otherwise>
						<i class="fas fa-lg fa-sort-numeric-up-alt">
							<a href="?page=${pagingNumber-1 }&categoryId=${categoryId}"
								class="board-number-change-color">${pagingNumber}</a>
						</i>
							&nbsp;
						</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- 다음 페이지 그룹 있나? -->
			<c:if test="${paging.hasNext==true}">
				<a href="?page=${requestScope.next}&categoryId=${categoryId}">
					<i class="fas fa-lg fa-angle-double-right"></i>
				</a>
			</c:if>
		</div>
	</div>
</body>
</html>