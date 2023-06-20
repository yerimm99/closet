<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>경매상품 리스트</title>
	<style type="text/css">
		table, td{border:1px solid black;border-collapse:collapse;}
		.text-center{margin-top:60px;text-align:center;}
		.text-center a{margin:0px 20px; display:inline-block}
		.row{width:100%;}
	</style>
</head>
<body>
	<c:set value="${paging}" var="paging"></c:set>
	<div class="row">
	<c:choose>
		<c:when test = "${productList == null}">
			<div class = "sell">
				경매 상품이 하나도 없습니다.
			</div>
		</c:when>
		<c:otherwise>
			<c:set var="i" value="0" />
			<c:set var="j" value="4" />
			<table border="1">
			<c:forEach items="${productList}" var="prod">
				<c:if test="${i%j == 0 }">
			    	<tr>
			    </c:if>
			    	<td>
			    		<a href = "<c:url value='/auction/detail.do'>
							<c:param name = 'productId' value='${prod.productId}' />
							</c:url>">
				   			<img src="<c:url value='${prod.picture1}'/>" width="250px" height="250px"/><br>
				    	  		<b>${prod.name}</b><br><br>
				      			<c:choose>
								  <c:when test="${empty prod.price}">
								    상품 최소가 : ${prod.startPrice}원
								  </c:when>
								  <c:otherwise>
								    현재 최고가 : ${prod.price}원
								  </c:otherwise>
								</c:choose>
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
	<!-- 페이징 처리 -->
		<div class="text-center">
			<!-- 이전 페이지 그룹 있나? -->
			<c:if test="${paging.hasPrevious==true }">
				<c:if test="${empty used}">
					<a href="?page=${preview}&categoryId=${categoryId}">
						<i class="fas fa-lg fa-angle-double-left"></i>
					</a>
				</c:if>
				<c:if test="${!empty used}">
					<a href="/auction/list2.do?page=${preview}&categoryId=${categoryId}&used=${used}">
						<i class="fas fa-lg fa-angle-double-left"></i>
					</a>
				</c:if>
				&nbsp;
			</c:if>
			<!-- 페이지 번호 표시 -->
			<c:forEach var="pagingNumber" begin="${paging.nowPageGroupStartPage }" end="${paging.nowPageGroupEndPage }">
				<c:choose>
					<c:when test="${pagingNumber==paging.nowPage }">
						<i class="fas fa-lg fa-sort-numeric-up-alt">
							<c:if test="${empty used}">
							<a href="?page=${pagingNumber-1 }&categoryId=${categoryId}"
								class="board-number-change-color abncc">${pagingNumber}
							</a>
							</c:if>
							<c:if test="${!empty used}">
							<a href="/auction/list2.do?page=${pagingNumber-1 }&categoryId=${categoryId}&used=${used}"
								class="board-number-change-color abncc">${pagingNumber}
							</a>
							</c:if>
						</i>
							&nbsp;
						</c:when>
					<c:otherwise>
						<i class="fas fa-lg fa-sort-numeric-up-alt">
							<c:if test="${empty used}">
							<a href="?page=${pagingNumber-1 }&categoryId=${categoryId}"
								class="board-number-change-color">${pagingNumber}</a>
							</c:if>
							<c:if test="${!empty used}">
							<a href="/auction/list2.do?page=${pagingNumber-1 }&categoryId=${categoryId}&used=${used}"
								class="board-number-change-color">${pagingNumber}</a>
							</c:if>
						</i>
							&nbsp;
						</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- 다음 페이지 그룹 있나? -->
			<c:if test="${paging.hasNext==true}">
				<c:if test="${empty used}">
				<a href="?page=${requestScope.next}&categoryId=${categoryId}">
					<i class="fas fa-lg fa-angle-double-right"></i>
				</a>
				</c:if>
				<c:if test="${!empty used}">
				<a href="/auction/list2.do?page=${requestScope.next}&categoryId=${categoryId}&used=${used}">
					<i class="fas fa-lg fa-angle-double-right"></i>
				</a>
				</c:if>
			</c:if>
		</div>
	</div>
</body>
</html>